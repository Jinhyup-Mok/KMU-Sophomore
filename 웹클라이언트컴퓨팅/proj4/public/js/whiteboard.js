window.addEventListener('load', function(){
    // 변수를 선언합니다.
    let color = 'black';
    let width = 1;
    let isDown = false;
    let newPoint, oldPoint;

    // 소켓을 연결합니다.
    const socket = io.connect();

    // 캔버스를 추출합니다.
    const canvas = document.querySelector('#canvas');
    const pen = document.querySelector('#pen');
    const eraser = document.querySelector('#eraser');
    const wid = document.querySelector('#wid');
    const context = canvas.getContext('2d');

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
            width: width
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
        color = 'black';
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

    // 소켓 이벤트를 연결합니다.
    socket.on('line', function (data) {
        console.log(data);
        console.log(canvas.getBoundingClientRect());
        context.beginPath();
        context.lineWidth = data.width;
        context.strokeStyle = data.color;
        context.moveTo(data.x1, data.y1);
        context.lineTo(data.x2, data.y2);
        context.stroke();
    });
});
