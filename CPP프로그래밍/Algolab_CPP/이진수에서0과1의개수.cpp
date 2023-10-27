/* 20223075 목진협
-이진수에서0과1의 개수*/
#include <iostream>
using namespace std;
int t, n, bin;
int main() {
    cin >> t;
    while(t--) {
        cin >> n;
        int zero = 0, one = 0;
        bin = 0;
        while(n > 0) {
            bin = n % 2;
            n /= 2;
            if(bin == 1) one++;
            else zero++;
        }
        cout << zero << " " << one << "\n";
    }
}
