var slides = document.querySelectorAll("#slides > img");
var prev = document.getElementById("prev");
var next = document.getElementById("next");
var slides2 = document.querySelector("#slides");
var current = 0;

showSlides(current);
prev.onclick = prevSlide;
next.onclick = nextSlide;

var buttons = document.querySelectorAll(".buttons");
for (let i = 0; i < buttons.length; i++) {
  buttons[i].onclick = animateSlide;
}

function showSlides(n) {
  slides2.style.left = -current*1200 + "px";
/*  for (let i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slides[n].style.display = "block";*/
}

function prevSlide() {
  if (current > 0) current -= 1;
  else
    current = slides.length - 1;
    showSlides(current);
}

function nextSlide() {
  if (current < slides.length - 1) current += 1;
  else
    current = 0;
    showSlides(current);  
}

function animateSlide() {
  var loc = parseInt(this.getAttribute("loc"))-1;
  if(loc>=0 && loc<buttons.length) current=loc;
  showSlides(current);  
}

//////////////

function newRegister() {
  var newItem = document.createElement("li");  // 요소 노드 추가
  var subject = document.querySelector("#subject");  // 폼의 텍스트 필드
  var newText = document.createTextNode(subject.value);  // 텍스트 필드의 값을 텍스트 노드로 만들기
  newItem.appendChild(newText);   // 텍스트 노드를 요소 노드의 자식 노드로 추가
  var newImage = document.createElement("img");
  newImage.src = "images/trash.png";
  newItem.appendChild(newImage);

  var itemList = document.querySelector("#itemList");  // 웹 문서에서 부모 노드 가져오기 
  itemList.prepend(newItem);  // 새로 만든 요소 노드를 부모 노드에 추가

  subject.value="";

  var imgs = document.querySelectorAll("li img");
  for(i=0; i<imgs.length; i++) {
    imgs[i].addEventListener("click", function() { // 항목 클릭했을 때 실행할 함수
      if(this.parentNode && this.parentNode.parentNode) {  // 부모 노드가 있다면 
        var is = this.parentNode;
        is.parentNode.removeChild(is);  // 부모 노드에서 삭제
      }
      });
  }
}

var ad = document.querySelector('.ad');
var box = document.querySelector('.box');
ad.onmouseover = function() {
    box.style.display="none";
    void box.offsetWidth;
    box.style.display="flex";
}


