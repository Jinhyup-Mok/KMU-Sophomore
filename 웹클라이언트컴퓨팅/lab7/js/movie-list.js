async function init() {
    const response = await fetch('http://localhost:8090/https://search.naver.com/search.naver?where=nexearch&sm=top_sug.pre&fbm=1&acr=2&acq=%EC%83%81%EC%98%81&qdt=0&ie=utf8&query=%EC%83%81%EC%98%81%EC%98%81%ED%99%94', {
    headers: {
      "X-Requested-With": "XMLHttpRequest"
    }
  });
  const body = response.text().then(function(html){
    var html_dom = new DOMParser().parseFromString(html,'text/html');
    console.log(html_dom.body);
    var movies = html_dom.querySelectorAll(".data_area");
    console.log(movies);
    display(movies);
  });
  }
  function display(movies) {
    const result = document.querySelector('#result');
    let string = '';
    movies.forEach((movie) => {
      var title = movie.querySelector(".area_text_box a").innerText;
      var genre = movie.querySelector(".info_group dd").innerText;
      // dl.info_group > dd
      var runtime = movie.querySelector(".info > dl:nth-child(1) > dd:nth-child(3)").innerText;
      // querySelectorAll(dl.info_group > dd)[1]
      var date = movie.querySelector(".info > dl:nth-child(2) > dd:nth-child(2)").innerText
      // querySelectorAll(dl.info_group)[1].querySelector("dd")
      var score = movie.querySelector(".num").innerText;
      var member = movie.querySelector(".type_ellip_2 > ._text")?.innerText;
      var poster = movie.querySelector("img").src;
      string += `<table><tr><th>영화</th><td>${title}</td></tr>
                  <tr><th>장르</th><td>${genre}</td></tr>
                  <tr><th>상영시간</th><td>${runtime}</td></tr>
                  <tr><th>개봉일</th><td>${date}</td></tr>
                  <tr><th>평점</th><td>${score}</td></tr>
                  <tr><th>출연</th><td>${member ? member : ""}</td></tr>
                  <tr><th>포스터</th><td><img width="178" height="260" src="${poster}"></td></tr></table>`;
    });            
    result.innerHTML = string;
  }  
  init();
  