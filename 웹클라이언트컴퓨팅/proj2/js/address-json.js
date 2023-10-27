
async function init() {
// MPF85hV_M_MgoJFe46Ms   uSvgQms2A1
    //var word = "합천군 삼가면 일부5길 1";
    var word = "창원시 진해구 웅동로 57번가길 7(마천동)";
    var query = encodeURI(word);
    const response = await fetch(`http://localhost:8090/https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&query=${query}`, {
      headers: {
    "Authorization": "KakaoAK ade06750b94c8bbd14fd3a4053a7c360"
  }
});
  const data = await response.json();
  console.log(data.documents[0]);
//  display(books);
}

function display(books) {
  const result = document.querySelector('#result');
  let string = '';
  books.forEach((book) => {
    string += `<table><tr><th>제목</th><td>${book.title}</td></tr>
                <tr><th>저자</th><td>${book.author}</td></tr>
                <tr><th>발행일</th><td>${book.pubdate}</td></tr>
              </table>`;
});
  result.innerHTML = string;
}

init();
