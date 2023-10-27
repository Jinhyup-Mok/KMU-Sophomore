// // 20223075 목진협 k번째숫자 구하기
#include <iostream>
using namespace std;

int t, n;
int main() {
    cin >> t;
    while(t--) {
        cin >> n;
        string result = "";
        for(int i=1; i<n+1; i++) {
           string x = to_string(i);
           result += x;
        }
        cout << result[n-1] << "\n";
    }
}
// #include <iostream>
// using namespace std;
// int t, n;
// int main() {
//     cin >> t;
//     while(t--) {
//         cin >> n;
//         int d = 1;
//         int cnt = 9;
//         int start = 1;
//         while (n > d * cnt) {
//             n -= d * cnt;
//             d++;
//             cnt *= 10;
//             start *= 10;
//         }
//         int num = start + (n - 1) / d;
//         int digit = (n - 1) % d;
//         cout << to_string(num)[digit] << '\n';
//     }
// }
