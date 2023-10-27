// 웹 서버를 생성하고 실행합니다.
const express = require("express");
const app = express();
const server = require("http").Server(app);
app.use(express.static(`${__dirname}/public`));
server.listen(52279, () => {
  console.log("Server Running at http://127.0.0.1:52279");
});

// 소켓 서버를 생성하고 실행합니다.
const io = require("socket.io")(server);

io.on("connection", (socket) => {
  socket.on("line", (data) => {
    io.sockets.emit("line", data);
  });
});

io.on("connection", (socket) => {
  socket.on("chat", (data) => {
    io.sockets.emit("chat", data);
  });
});

io.on("connection", (socket) => {
  socket.on("reseted", (data) => {
    io.sockets.emit("reseted", data);
  });
});