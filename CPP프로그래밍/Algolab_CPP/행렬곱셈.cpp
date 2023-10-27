#include <iostream>
using namespace std;
int test, r, s, t, sum;
int main() {
    cin >> test;
    while(test--) {
        cin >> r >> s >> t;
        int matrix_a[r][s];
        int matrix_b[s][t];
        int result[101][101] = {};
        for(int i=0; i<r; i++) {
            for(int j=0; j<s; j++) {
                cin >> matrix_a[i][j];
            }
        }
        for(int i=0; i<s; i++) {
            for(int j=0; j<t; j++) {
                cin >> matrix_b[i][j];
            }
        }
        for(int i=0; i<r; i++) {
            for(int j=0; j<s; j++) {
                for(int k=0; k<t; k++) {
                    result[i][k] += matrix_a[i][j] * matrix_b[j][k];
                }
            }
        }
        for(int i=0; i<r; i++) {
            for(int j=0; j<t; j++) {
                cout << result[i][j] << " ";
            } cout << "\n";
        }
    }
}
