/* 20223075 목진협
- 주어진정수의 최대최소 구하기 */

#include <iostream>
using namespace std;
int t, n;

int main() {
    cin >> t;
    for(int i=0; i<t; i++) {
        cin >> n;
        int num_f;
        int num;
        cin >> num_f;
        int max = num_f;
        int min = num_f;
        for(int j=1; j<n; j++) {
            cin >> num;
            if(max < num) max = num;
            if(min > num) min = num;
        }
        cout << max << " " << min << "\n";
    }
}