/* 20223075 목진협 11의 배수 */
#include <iostream>
#include <algorithm>
using namespace std;
int t;
int main() {
    cin >> t;
    string n;
    while(t--) {
        cin >> n;
        string str;
        int num[n.length()];
        for(int i=0; i<n.length(); i++) {
            num[i] = n[i]-'0';
        }
        int size = sizeof(num)/sizeof(num[0]);
        int last;
        while(size != 1) {
            last = num[size-1];
            size--;
            if(last > num[size-1]) {
                num[size-1] += 10;
                num[size-1] -= last;
                num[size-2]--;
            } else {
                num[size-1] -= last;
            }
            str += to_string(last);
        }
        reverse(str.begin(), str.end());
        int idx;
        for (int i = 0; i < str.length(); i++) {
            if (str[i] <= 48) continue;
            else idx = i; break;
        }
        str = str.substr(idx);
        if (num[0] == 0) cout << str << "\n";
        else cout << 0 << "\n";
    }
}