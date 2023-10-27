/* 20223075 목진협
숫자의 모든 자리수 반복 곱하기*/
#include <iostream>

using namespace std;
int t;
unsigned long n, mod, mul; 
// div 몫 저장, mod 나머지 값 저장, mul 자리수를 곱하여 탄생한 새로운 수

int main() {
    cin >> t;
    while(t--) {
        cin >> n;
        mul = 1;
        while(n > 0) {
            mod = n % 10;
            n /= 10;
            if(mod != 0) {
                mul *= mod;
            }
            
            if((n / 10 == 0) && (n != 0)) {
                n *= mul;
                mul = 1;
            }
        }
        cout << mod << "\n";
    }
}