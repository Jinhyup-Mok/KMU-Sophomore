#include <iostream>
using namespace std;
int t, y, m, d;;
int main() {
    cin >> t;
    int leap_year_day[13] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int year[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    while(t--) {
        int n = 0;
        int cnt = 0;
        cin >> y >> m >> d;
        n = (y - 1) * 365;
        for (int i = 1; i < y; i++) if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) cnt++;
        n += cnt;
        for (int i = 1; i < m; i++) {
            if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) n += leap_year_day[i];
            else n += year[i];
        }
        n += d;
        cout << (n % 7) << endl;
    }
}