// Listen on a specific host via the HOST environment variable
var host = 'localhost' || '0.0.0.0';
// Listen on a specific port via the PORT environment variable
var port = process.env.PORT || 8090;

var cors_proxy = require('cors-anywhere');
cors_proxy.createServer({
    originWhitelist: [], // Allow all origins
    requireHeader: ['origin', 'x-requested-with'],
    removeHeaders: ['cookie', 'cookie2']
}).listen(port, host, function() {
    console.log('Running CORS Anywhere on ' + host + ':' + port);
});

// port 초기화
// lsof -i TCP:포트번호  로 PID 찾기
// kill -9 (PID)로 포트 초기화