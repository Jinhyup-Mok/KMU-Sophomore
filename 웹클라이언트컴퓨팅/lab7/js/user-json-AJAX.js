async function init() {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "https://jsonplaceholder.typicode.com/users");
  xhr.send();
  
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let users = JSON.parse(xhr.responseText);
      console.log(users);  
          // 응답결과(xhr.responseText)를 객체형태(users)로 변환한 값을 브라우저의 콘솔창에 디버깅 용도로 출력함.
          // 객체 내부의 키 이름들(name, username, email,...)을 확인함.
          // 브라우저 콘솔창은 브라우저 내부에 마우스를 올려놓고 <F12>키를 누르면 개발자창이 뜨며 아래쪽에 콘솔창이 보임.
          // 현재는 10개의 객체가 배열안에 있으므로 콘솔창의 "Array" 왼쪽 삼각형을 클릭하면 내부의 10개 객체가 나타남.
          // (실습6)의 도서정보객체 내부의 출력키 이름을 확인하는 용도로 사용할 수 있음.
          // (힌트1) 사용자(users) 배열구조 결과와 달리, 네이버도서검색결과(xhr.responseText를 객체로 변환한 결과)는 도서검색 배열값이
          //            내부의<items>키에 저장되어 있음. 

      // (발행일 기준 올림차순으로 객체 정렬하는 실행문 위치임)
      // (힌트2) 배열전용 내부메소드(sort())에 정렬 비교를 위한 함수등록을 하여 정렬하는 것이 효과적임
      //            구글링으로 내부메소드 사용방법을 이해한 후 현재 위치에 적용함.

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
