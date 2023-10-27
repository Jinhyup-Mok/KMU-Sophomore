var slides = document.querySelector("#slides");
var slidesImage = document.querySelectorAll("#slides > img");
var prev = document.getElementById("prev");
var next = document.getElementById("next");

var current = 0;

showSlides(current);
prev.onclick = prevSlide;
next.onclick = nextSlide;

function showSlides(n) {
  console.log(n);
  slides.style.transform = `translateX(${n * -1200}px)`;
}

function prevSlide() {
  if (current > 0) current -= 1;
  else current = slidesImage.length - 1;
  showSlides(current);
}

function nextSlide() {
  if (current < slidesImage.length - 1) current += 1;
  else current = 0;
  showSlides(current);
}

function handleIndex(target) {
  current = Number(target.value) - 1;
  showSlides(current);
}

function newRegister() {
  var newItem = document.createElement("li"); // 요소 노드 추가
  var subject = document.querySelector("#subject"); // 폼의 텍스트 필드
  var newText = document.createTextNode(subject.value); // 텍스트 필드의 값을 텍스트 노드로 만들기
  var newTrash = document.createElement("img");
  newTrash.src = "images/trash.png";
  newTrash.id = "trash";
  newTrash.style.width = "25px";
  newItem.style.display = "flex";
  newItem.style.justifyContent = "space-between";
  newItem.style.margin = "10px";
  newTrash.onclick = function () {
    newItem.remove();
  };
  newItem.appendChild(newText); // 텍스트 노드를 요소 노드의 자식 노드로 추가
  newItem.appendChild(newTrash);

  var itemList = document.querySelector("#itemList"); // 웹 문서에서 부모 노드 가져오기
  itemList.appendChild(newItem); // 새로 만든 요소 노드를 부모 노드에 추가

  subject.value = "";
}

var ad = document.querySelector(".ad");
var box = document.querySelector(".box");
ad.onmouseover = function () {
  box.style.display = "none";
  void box.offsetWidth;
  box.style.display = "flex";
};

