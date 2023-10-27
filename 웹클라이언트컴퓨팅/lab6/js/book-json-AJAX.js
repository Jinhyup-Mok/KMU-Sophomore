async function init() {

  var word = "고경희 웹";
  var query = encodeURI(word);
  let xhr = new XMLHttpRequest();
  xhr.open("GET", `http://localhost:8090/https://openapi.naver.com/v1/search/book.json?query=${query}&display=10&start=1`);   //프록시서버포트(8090)
  xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
  xhr.setRequestHeader("X-Naver-Client-Id", "ypOJF8IW8B_AGzNskI4c");
  xhr.setRequestHeader("X-Naver-Client-Secret", "OF7KGm7K1G");
  xhr.send();

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let books = JSON.parse(xhr.responseText);
      display(books);
    }
  };
}

function display(books) {
  const result = document.querySelector('#result');
  let string = '';
  books.items.sort((x,y) => x.pubdate - y.pubdate);
  books.items.forEach((book) => {
    string += `<table><tr><th>제목</th><td>${book.title}</td></tr>
                <tr><th>저자</th><td>${book.author}</td></tr>
                <tr><th>발행일</th><td>${book.pubdate}</td></tr>
              </table>`;
  });
  result.innerHTML = string;
}

init();
