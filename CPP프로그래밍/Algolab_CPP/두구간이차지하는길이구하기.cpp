// 20223075 목진협 두구간이차지하는 길이 구하기
#include <iostream>
using namespace std;
int t, a, b, c, d;
int interval, max_interval;
int main() {
    cin >> t;
    while(t--) {
        cin >> a >> b >> c >> d;
        if(a > d || c > b) {
            interval = 0;
            max_interval = (b-a) + (d-c);
        } else {
           max_interval = max(b,d) - min(a,c);
           interval = min(b,d) - max(a,c);
        }
        cout << interval << " " << max_interval << "\n";
    }
}