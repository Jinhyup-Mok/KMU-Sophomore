// 모듈을 추출합니다.
const express = require('express');
const cors = require("cors");

// 서버를 생성/실행합니다.
const app = express();
app.listen(52273, () => {
    console.log('Server Running at http://127.0.0.1:52273');
});

// 미들웨어를 추가합니다.
app.use(cors());
app.use(express.urlencoded({
    extended: false
}))

// 변수를 선언합니다.
let userCounter = 2;
const users = [
    {
        id: 0,
        name: "홍길동",
        region: "서울"
    },
    {
        id: 1,
        name: "김철수",
        region: "부산"
    }
];

// 라우트합니다.
app.get('/user', (request, response) => {
    if (users.length > 0)
        response.send(users);
    else
        response.status(404).send({status: '데이터가 존재하지 않습니다.'});
});

app.get('/user/:id', (request, response) => {
    // 변수를 선언합니다.
    const id = request.params.id;
    // 데이터를 찾습니다.
    const filtered = users.filter((user) => user.id == id);
    // 응답합니다.
    if (filtered.length == 1)
        response.send(filtered[0]);
    else
        response.status(404).send({status: '데이터가 존재하지 않습니다.'});
});

app.post('/user', (request, response) => {
    // 변수를 선언합니다.
    const body = request.body;
    // 예외를 처리합니다.
    if (!body.name)
        return response.status(400).send({status: 'name을 보내주세요'});
    else if (!body.region)
        return response.status(400).send({status: 'region을 보내주세요'});
    // 변수를 추출합니다.
    const name = body.name;
    const region = body.region;
    // 데이터를 저장합니다.
    const data = {
        id: userCounter++,
        name: name,
        region: region
    };
    users.push(data);
    // 응답합니다.
    response.send(data);
});

app.put('/user/:id', (request, response) => {
    // 변수를 선언합니다.
    const id = request.params.id;
    const index = users.findIndex((user) => user.id == id)
    if (index>=0) {
        // 데이터가 존재한다면
        if (request.body.name)
            users[index].name = request.body.name;
        if (request.body.region)
            users[index].region = request.body.region;
        // 응답합니다.
        response.send(users[index])
    } else {
        // 데이터가 존재하지 않는다면
        // 응답합니다.
        response.status(404).send({status: '데이터가 존재하지 않습니다.'});
    }
});

app.delete('/user/:id', (request, response) => {
    // 변수를 선언합니다.
    const id = request.params.id
    const index = users.findIndex((user) => user.id == id)
    // 데이터를 제거합니다.
    if (index != -1) {
        users.splice(index, 1);
        response.send({status: '제거했습니다.'});
    } else {
        response.status(404).send({status: '데이터가 존재하지 않습니다.'});
    }
});