// 20223075 목진협 - 주가분석-1
#include <iostream>
int t, n, num;
int main() {
    std::cin >> t;
    while(t--) {
        std::cin >> n;
        int l=-1, m=-1, r, cnt=0;
        while(n--) {
            std::cin >> num;
            if (l == -1) {l = num; continue;}
            if (m == -1) {m = num; continue;}r = num;
            if (m == r) continue;
            else if (l < m && m > r) cnt++;
            l = m; m = r;
        }
        std::cout << cnt << "\n";
    }
}