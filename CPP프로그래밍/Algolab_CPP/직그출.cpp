/*20223075 목진협
-직선그래프출력하기 */
#include <iostream>
using namespace std;
int t, k;
int main() {
    cin >> t;
    while(t--) {
        cin >> k;
        for (int i = 1; i <= k; i++) {
            for(int j = 1; j <= k; j++) {
                if (i == (k/2) + 1 && j == (k/2) + 1) cout << "O";
                else if (j == (k/2) + 1) cout << "I";
                else if (i == (k/2) + 1) cout << "+";
                else if (j == k - i + 1) cout << "*";
                else cout << ".";
            }
            cout << "\n";
        }
    }
}