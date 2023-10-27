async function init() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "https://jsonplaceholder.typicode.com/users");
  xhr.send();
  
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let users = JSON.parse(xhr.responseText);
      console.log(users);
      display(users);
    }
  };
}

function display(users) {
  const result = document.querySelector('#result');
  let string = '';
  users.forEach((user) => {
    string += `<table><tr><th>이름</th><td>${user.name}</td></tr>
                <tr><th>아이디</th><td>${user.username}</td></tr>
                <tr><th>이메일</th><td>${user.email}</td></tr>
              </table>`;
  });
  result.innerHTML = string;
}

init();
