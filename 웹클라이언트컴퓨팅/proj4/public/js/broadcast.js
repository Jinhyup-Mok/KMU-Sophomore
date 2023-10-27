window.addEventListener('load', function(){
  // 변수를 선언합니다.
  //let color = 'black';
  let width = 1;
  let isDown = false;
  let color = 'black';
  let newPoint, oldPoint;

  // 소켓을 연결합니다.
  const socket = io.connect();

  // 캔버스를 추출합니다.
  const canvas = document.querySelector('#canvas');
  const pen = document.querySelector('#pen');
  const eraser = document.querySelector('#eraser');
  const wid = document.querySelector('#wid');
  const pencolor = document.querySelector('#pencolor');
  const resetcanvas = document.querySelector('#reset');
  const context = canvas.getContext('2d');

  var msgcount = 1;

  // 마우스 이벤트를 연결합니다.
  canvas.addEventListener('mousedown', function (event) {
      isDown = true;
      const rect = canvas.getBoundingClientRect();
      oldPoint = {
          x: event.clientX - rect.left,
          y: event.clientY - rect.top
      };
  });

  canvas.addEventListener('mousemove', function(event) {
      if (!isDown) { return; }
      const rect = canvas.getBoundingClientRect();
      newPoint = {
          x: event.clientX - rect.left,
          y: event.clientY - rect.top
      };
      socket.emit('line', {
          x1: oldPoint.x,
          y1: oldPoint.y,
          x2: newPoint.x,
          y2: newPoint.y,
          color: color,
          width: width,
          reset: false,
          message: ""
      });
      oldPoint = newPoint;
  });

  canvas.addEventListener('mouseup', function(event) {
      isDown = false;
      const rect = canvas.getBoundingClientRect();
      oldPoint = {
          x: event.clientX - rect.left,
          y: event.clientY - rect.top
      };
  });

  // 입력 양식 이벤트를 연결합니다.
  pen.addEventListener('click', function(event) {
      width = 1;
      wid.value=width;
  });

  eraser.addEventListener('click', function(event) {
      width = 10;
      color = 'white';
      wid.value=width;
  });

  wid.addEventListener('change', function(event) {
      width=wid.value;
  });

  pencolor.addEventListener('click', function(event){
      color = document.getElementById("pencolor").options[document.getElementById("pencolor").selectedIndex].value;
  });

  resetcanvas.addEventListener('click', function(){
      //console.log('reset');
      socket.emit('line', {
          reset: true,
          message: ""
      });
  });

  const chat = document.getElementById("chat");

  chat.addEventListener('submit', function(){
      var id = document.getElementById("name").value;
      var message = document.getElementById("message").value;
      const fullmessage = `[#${msgcount}](${id}) ${message}\n`;

      socket.emit('line',{
          message: fullmessage
      });
      //console.log(fullmessage);
  });

  // 소켓 이벤트를 연결합니다.
  socket.on('line', function (data) {
      if(data.message != ""){
          msgcount+=1;
          var textarea_str = document.getElementById("chatbox").value;
          textarea_str += data.message;
          document.getElementById("chatbox").innerHTML = textarea_str;              
      }

      else if(data.reset){
          console.log('reset');
          context.clearRect(0, 0, canvas.width, canvas.height);
          context.beginPath();
          context.stroke(); 
      }
      
      else{
          console.log(data);
          console.log(canvas.getBoundingClientRect());
          context.beginPath();
          context.lineWidth = data.width;
          context.strokeStyle = data.color;
          context.moveTo(data.x1, data.y1);
          context.lineTo(data.x2, data.y2);
          context.stroke();
      }
  });


  // socket.on('msg', (data) => {
  //     console.log('send');
  //     var textarea_str = document.getElementById("messagebox").value;
  //     textarea_str += data;
  //     document.getElementById("messagebox").innerHTML(textarea_str);
  // });

  socket.on('messages', function(data) {
      console.log('send');
  });
});