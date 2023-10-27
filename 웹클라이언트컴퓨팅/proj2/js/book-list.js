
async function init() {
  const response = await fetch('http://localhost:8090/https://www.hanbit.co.kr/store/books/new_book_list.html', {
  headers: {
    "X-Requested-With": "XMLHttpRequest"
  }
});
const body = response.text().then(function(html){
  var html_dom = new DOMParser().parseFromString(html,'text/html');
  console.log(html_dom.body);
  var books = html_dom.querySelectorAll(".view_box");
  console.log(books);
  display(books);
});
}

function display(books) {
  const result = document.querySelector('#result');
  let string = '';
  books.forEach((book) => {
    var title = book.querySelector(".book_tit a").innerText;
    var author = book.querySelector(".book_writer").innerText;
    var price = book.querySelector(".price").innerText;
    var local = book.querySelector(".view_box_block > img").src;
    var poster = "https://www.hanbit.co.kr/" + local.substr(local.indexOf("/data"));
    string += `<table><tr><th>제목</th><td>${title}</td></tr>
                <tr><th>저자</th><td>${author}</td></tr>
                <tr><th>가격</th><td>${price}</td></tr>
                <tr><th>표지</th><td><img width="178" height="260" src="${poster}"></td></tr></table>`;
  });            
  result.innerHTML = string;
}

init();
