/* 20223075 목진협
-성적*/

#include <iostream>

using namespace std;

int t, n, score;
int main() {
    cin >> t;
    
    while(t--) {
        cin >> n;
        int A=0, B=0, C=0, D=0, F=0;
        
        for(int i=0; i<n; i++) {
            cin >> score;
            if(score >= 90) A++;
            else if(score >= 80) B++;
            else if(score >= 70) C++;
            else if(score >= 60) D++;
            else F++;
        }
        cout << A << " " << B << " " << C << " " << D << " " << F << "\n";
    }
}
