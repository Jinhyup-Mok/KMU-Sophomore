/* 20223075 목진협
- 직선 그래프 출력하기 */

#include <iostream>
using namespace std;

int t, k;
char grp[100][100];

int main() {
    cin >> t;

    while(t--) {    
        cin >> k;
                
        int mid = (k/2 + 1)-1;

        for(int i=0; i<k; i++) {
            for(int j=0; j<k; j++) {
                grp[i][j] = '.';
                if(i == mid) {
                    grp[i][j] = '+';
                } else if(j == mid) {
                    grp[i][j] = 'I';
                }
            }
        }
        for(int i=0; i<k; i++) {
            grp[i][(k-1)-i] = '*';    
        }
        grp[mid][mid] = 'O';
        
        for(int i=0; i<k; i++) {
            for(int j=0; j<k; j++) {
                cout << grp[i][j];
            }
            cout << "\n";
        }
    }
}