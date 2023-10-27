/* 20223075 목진협
- 끝자리숫자구하기 */
#include <iostream>

using namespace std;
int t;
int main() {
    cin >> t;
    // 마지막 자리수 구하는거 -> 마지막 자리수 끼리만 연산해주면 된다.
    int n;
    int num;
    for(int i=0; i<t; i++) {
        cin >> n;
        long mul = 1;
        for(int j=0; j<n; j++) {
            cin >> num;
            num %= 10;
            mul *= num;
            mul %= 10;
        }
        cout << mul << "\n";
    }
}