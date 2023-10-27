async function drawMap() {
  const lat = 37.55684;
  const lng = 126.91404;

  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
  mapOption = { 
    center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표 - 이지스퍼블리싱
    level: 14 // 지도의 확대 레벨
  };

  // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
  var map = new kakao.maps.Map(mapContainer, mapOption); 

  // 마커 클러스터러를 생성합니다 
  var clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    minLevel: 10 // 클러스터 할 최소 지도 레벨 
  });

  // 오픈 데이터 서버에서 경상남도 전통시장 정보 가져오기
  let markets = await getMarkets();
  console.log(markets);
  let locations = await getLocations(markets);
  console.log(`시장갯수: ${locations.length}`);

  // 마커들을 모아놓을 변수
  var markers = [];
  for(let i = 0; i < locations.length; i++) { 
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(locations[i].y, locations[i].x)
    });

    markers.push(marker);   // 마커를 배열에 추가합니다

    var infowindow = new kakao.maps.InfoWindow( {
      content : `${locations[i].title}(${locations[i].openday})<br>${locations[i].addr}<br>경도:${locations[i].x}<br>위도:${locations[i].y}`   // 인포윈도우에 표시할 내용
    });

    // 마커에 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
    // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    // 마커에 마우스오버하면 makeOverListener() 실행
    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));  
    // 마커에서 마우스아웃하면 makeOutListener() 실행
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));   
  }

  clusterer.addMarkers(markers);
}

async function getMarkets() {
  const  url = 'http://apis.data.go.kr/6480000/gyeongnammarket/gyeongnammarketList?serviceKey=nMUhKE9aQR%2FhZKPveXRcjEkjkQrO22vf0kdG%2FgAoS8GnLVr5fBj2h93uvvLPu3xBL3R%2B50MN%2FboRFAAgOROmXg%3D%3D&numOfRows=78&pageNo=1&resultType=json';
  var res = await fetch(url);
  var json = await res.json();
  const markets = json.gyeongnammarketList.body.items.item;
  return markets;
}

async function getLocations(markets) {
  var waitingList = [],
      locations = [];
  markets.forEach((market, i) => {
    waitingList[i] = getLocation(market, locations);
  });
  await synchronization(waitingList);
  return locations;
}

async function getLocation(market, locations) {
  var address = `${market.area} ${market.address}`;
  var location = await addrToLocation(address);
  if(location && location.address_name && location.x && location.y) {
    locations.push({
      addr : location.address_name,
      x : location.x,
      y : location.y,
      title: market.title,
      openday: market.openday,
    });
  }
};

async function addrToLocation(address) {
  var query = encodeURI(address);
  const response = await fetch(`https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&query=${query}`, {
    headers:  {
                "Authorization": "KakaoAK ade06750b94c8bbd14fd3a4053a7c360"
              }
  });
  const data = await response.json();
  const location = data.documents[0];
  console.log(location);
  return location;
}

async function synchronization(waitingList) {
  return Promise.all(waitingList);
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
  return function() {
    infowindow.open(map, marker);          
  };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
  return function() {
    infowindow.close();
  };
}

var mapdraw = document.querySelector("#mapdraw");
mapdraw.onclick = drawMap;
