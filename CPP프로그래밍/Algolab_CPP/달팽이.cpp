/* 20223075 목진협
-숫자정사각형달팽이모양출력하기*/
#include <iostream>
using namespace std;
int t, n, a, b;
int main() {
    cin >> t;
    while (t--) {
        cin >> n >> a >> b;
        int k=0, num = 0, idx = 0;
        int snail = n;
        while(num != (snail*snail)/2 + 1) {
            switch(k) {
                case 0:
                    for (int i = 0; i < n; i++) {
                        num++; idx++;
                        if ((idx >= a) && (idx <= b)) cout << num << " ";
                    } n--; k++;
                case 1:
                    for (int i = 0; i < n; i++) {
                        num = num + snail;
                        idx++;
                        if ((idx >= a) && (idx <= b)) cout << num << " ";
                    } k++;
                case 2:
                    for (int i = 0; i < n; i++) {
                        num--; idx++;
                        if ((idx >= a) && (idx <= b)) cout << num << " ";
                    } n--; k++;
                case 3:
                    for (int i = 0; i < n; i++) {
                        num = num - snail;
                        idx++;
                        if ((idx >= a) && (idx <= b)) cout << num << " ";
                    } k++;
                default: k = 0;
            }
            if(idx > b) break;
        }
        cout << "\n";
    }
}