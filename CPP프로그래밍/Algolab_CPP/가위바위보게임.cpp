/* 20223075 목진협
- 가위바위보 게임 */

#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;
int t, n;
int main() {
    cin >> t;
    for(int i=0; i<t; i++) {
        cin >> n;
        int rsp;
        int answer = 0;
        int r = 0;
        int s = 0;
        int p = 0;
        for(int j=0; j<n; j++) {
            cin >> rsp;
            if(rsp == 1) r++;
            else if(rsp == 2) s++;
            else p++;
            if(r != 0 && s != 0 && p != 0) answer = 0;
            else if(r > 0 && s > 0 && p == 0) answer = max(r, s);
            else if(r > 0 && s == 0 && p > 0) answer = max(r, p);
            else if(r == 0 && s > 0 && p > 0) answer = max(s, p);
        }
        cout << answer << "\n";
    }
}