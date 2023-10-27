#include <iostream>
using namespace std;

int t, n;

int main() {
    cin >> t;
    while(t--) {
        cin >> n;
        int ms[50][50] = {};
        ms[0][n/2] = 1;
        int x = 0;
        int y = n/2;
        for(int i=2; i<=n*n; i++) {
            if(x-1 < 0) {
                if(y+1 > n-1) y = 0;
                else y += 1;
                if(ms[n-1][y] != 0) {
                    x += 1;
                    if(y == 0) y = n-1;
                    else y -= 1;
                } else {
                    x = n-1;
                }
                ms[x][y] = i;
            } else {
                if(y+1 > n-1) y = 0;
                else y += 1;
                if(ms[x-1][y] != 0) {
                    x += 1;
                    if(y == 0) y = n-1;
                    else y -= 1;
                } else {
                    x -= 1;
                }
                ms[x][y] = i;
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                cout << ms[i][j] << " ";
            }
            cout << "\n";
        }
    }
}