
async function init() {
// MPF85hV_M_MgoJFe46Ms   uSvgQms2A1
    var word = "고경희 HTML 웹"; //"자바스크립트";
    var query = encodeURI(word);
    //const response = await fetch(`https://openapi.naver.com/v1/search/book.json?query=${query}&display=10&start=1`, {
    const response = await fetch(`http://localhost:8090/https://openapi.naver.com/v1/search/book.json?query=${query}&display=10&start=1`, {
      headers: {
    "X-Requested-With": "XMLHttpRequest",
    "X-Naver-Client-Id": "MPF85hV_M_MgoJFe46Ms",
    "X-Naver-Client-Secret": "uSvgQms2A1"
  }
});
  const data = await response.json();
  const books = data.items.sort((a,b) => b.pubdate - a.pubdate);
  console.log(books);
  display(books);
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
