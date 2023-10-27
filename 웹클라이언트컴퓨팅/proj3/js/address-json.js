
async function init() {
  var addresses = ["합천군 삼가면 일부5길 1", "창원시 진해구 웅동로 57번가길 7(마천동)","김해시 진례면 송현로 6-1"];
  const result = document.querySelector('#result');
  var string = "";
  for(i=0; i<addresses.length; i++) {
    var query = encodeURI(addresses[i]);
    const response = await fetch(`https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&query=${query}`, {
      headers:  {
                  "Authorization": "KakaoAK ade06750b94c8bbd14fd3a4053a7c360"
                }
    });
    const data = await response.json();
    const locations = data.documents;
    console.log(locations);
    if(locations.length>0)
      string += display(locations[0]);
  }
  result.innerHTML = string;
}


function display(location) {
  return `<table><tr><th>주소</th><td>${location.address.address_name}</td></tr>
            <tr><th>경도</th><td>${location.address.x}</td></tr>
            <tr><th>위도</th><td>${location.address.y}</td></tr>
            </table>`;
}

init();
