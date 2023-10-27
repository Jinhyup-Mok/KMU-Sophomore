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
let movieCounter = 3;
const movies = [
    {
        id: 0,
        title: "드림",
        genre: "코미디",
        rating: 8.01,
        actor: "박서준, 아이유, 김종수, 고창석, 정승길, 이현우",
        poster: "https://search.pstatic.net/common?type=o&amp;size=174x246&amp;quality=100&amp;direct=true&amp;src=https%3A%2F%2Fs.pstatic.net%2Fmovie.phinf%2F20230403_204%2F1680507641170NKvOl_JPEG%2Fmovie_image.jpg%3Ftype%3Dw640_2"
    },
    {
        id: 1,
        title: "존 윅 4",
        genre: "액션",
        rating: 8.41,
        actor: "키아누 리브스, 견자단, 빌 스카스가드, 로렌스 피시번, 이안 맥쉐인, 사나다 히로유키",
        poster: "https://search.pstatic.net/common?type=o&amp;size=174x246&amp;quality=100&amp;direct=true&amp;src=https%3A%2F%2Fs.pstatic.net%2Fmovie.phinf%2F20230329_142%2F1680055399492ipxkq_JPEG%2Fmovie_image.jpg%3Ftype%3Dw640_2"
    },
    {
        id: 2,
        title: "스즈메의 문단속",
        genre: "애니메이션",
        rating: 8.10,
        actor: "하라 나노카, 마츠무라 호쿠토, 후카츠 에리, 마츠모토 코시로, 소메타니 쇼타, 이토 사이리",
        poster: "https://search.pstatic.net/common?type=o&amp;size=174x246&amp;quality=100&amp;direct=true&amp;src=https%3A%2F%2Fs.pstatic.net%2Fmovie.phinf%2F20230206_264%2F1675649061557DaJHD_JPEG%2Fmovie_image.jpg%3Ftype%3Dw640_2"
    }
];

// 라우트합니다.
app.get('/movie', (request, response) => {
    if (movies.length > 0)
        response.send(movies);
    else
        response.status(404).send({status: '데이터가 존재하지 않습니다.'});
});

app.get('/movie/:id', (request, response) => {
    // 변수를 선언합니다.
    const id = request.params.id;
    // 데이터를 찾습니다.
    const filtered = movies.filter((movie) => movie.id == id);
    // 응답합니다.
    if (filtered.length == 1)
        response.send(filtered[0]);
    else
        response.status(404).send({status: '데이터가 존재하지 않습니다.'});
});

app.post('/movie', (request, response) => {
    // 변수를 선언합니다.
    const body = request.body;
    // 예외를 처리합니다.
    if (!body.title)
        return response.status(400).send({status: '영화제목을 보내주세요'});
    else if (!body.genre)
        return response.status(400).send({status: '장르를 보내주세요'});
    else if (!body.rating)
        return response.status(400).send({status: '평점을 보내주세요'});
    else if (!body.actor)
        return response.status(400).send({status: '출연배우를 보내주세요'});
    else if (!body.poster)
        return response.status(400).send({status: '포스터를 보내주세요'});
    // 변수를 추출합니다.
    const title = body.title;
    const genre = body.genre;
    const rating = body.rating;
    const actor = body.actor;
    const poster = body.poster;
    // 데이터를 저장합니다.
    const data = {
        id: movieCounter++,
        title: title,
        genre: genre,
        rating: rating,
        actor: actor,
        poster: poster,
    };
    movies.push(data);
    // 응답합니다.
    response.send(data);
});

app.put('/movie/:id', (request, response) => {
    // 변수를 선언합니다.
    const id = request.params.id;
    const index = movies.findIndex((movie) => movie.id == id)
    if (index>=0) {
        // 데이터가 존재한다면
        if (request.body.title)
            movies[index].title = request.body.title;
        if (request.body.genre)
            movies[index].genre = request.body.genre;
        if (request.body.rating)
            movies[index].rating = request.body.rating;
        if (request.body.actor)
            movies[index].actor = request.body.actor;
        if (request.body.poster)
            movies[index].poster = request.body.poster;
        // 응답합니다.
        response.send(movies[index])
    } else {
        // 데이터가 존재하지 않는다면
        // 응답합니다.
        response.status(404).send({status: '데이터가 존재하지 않습니다.'});
    }
});

app.delete('/movie/:id', (request, response) => {
    // 변수를 선언합니다.
    const id = request.params.id
    const index = movies.findIndex((movie) => movie.id == id)
    // 데이터를 제거합니다.
    if (index != -1) {
        movies.splice(index, 1);
        response.send({status: '제거했습니다.'});
    } else {
        response.status(404).send({status: '데이터가 존재하지 않습니다.'});
    }
});