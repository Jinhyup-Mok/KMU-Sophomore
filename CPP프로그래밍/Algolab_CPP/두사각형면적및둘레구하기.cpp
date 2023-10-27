// 20223075 목진협 두상각형면적및둘레구하기
#include <iostream>
#include <cstdlib>
using namespace std;
int t;
int main() {
    cin >> t;
    while(t--) {
        int px1, py1, qx1, qy1;
        int px2, py2, qx2, qy2;
        int s = 0, r = 0; // 면적, 둘레
        cin >> px1 >> py1 >> qx1 >> qy1;
        cin >> px2 >> py2 >> qx2 >> qy2;
        int xy = max(qx1, qx2);
        
        int *xl = new int[xy]();
        int *yl = new int[xy]();
        int cntx = 0, cnty = 0;
        
        if (qx1 < px2 || qx2 <px1 || qy1 < py2 || qy2 < py1) {
            r += abs(qx1 -px1) + abs(qy1 - py1) + abs(qx2 - px2) + abs(qy2 - py2);
            r *= 2;
            s += abs(qx1 -px1) * abs(qy1 - py1) + abs(qx2 - px2) * abs(qy2 - py2);
        } else {
            for (int i =px1; i < qx1; i++) {
                xl[i] += 1;
                if(xl[i] == 2) cntx++;
            }
            for (int i = px2; i < qx2; i++) {
                xl[i] += 1;
                if(xl[i] == 2) cntx++;
            }
            for (int i = py1; i < qy1; i++) {
                yl[i] += 1;
                if(yl[i] == 2) cnty++;
            }
            for (int i = py2; i < qy2; i++) {
                yl[i] += 1;
                if(yl[i] == 2) cnty++;
            }
            r += abs(qx1 -px1) + abs(qy1 - py1) + abs(qx2 - px2) + abs(qy2 - py2);
            r *= 2;
            r -= (cntx + cnty) * 2;
            s += abs(qx1 -px1) * abs(qy1 - py1) + abs(qx2 - px2) * abs(qy2 - py2);
            s -= (cntx * cnty);
        }
        cout << s << " " << r << endl;
    }
}