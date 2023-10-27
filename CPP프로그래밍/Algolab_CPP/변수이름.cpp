#include <iostream>
using namespace std;
int t;
int main() {
    cin >> t;
    while(t--) {
        int n = 1;
        string str = "";
        cin >> str;
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 && 48 <= str[i] && 57 >= str[i]) {
                n = 0;
                break;
            }
            if ((48 > str[i] || (57 < str[i] && str[i] < 65) || (90 < str[i] && str[i] < 97) || str[i] > 122) && str[i] != 95) {
                n = 0;
                break;
            }
        }
        cout << n << "\n";
    }
}