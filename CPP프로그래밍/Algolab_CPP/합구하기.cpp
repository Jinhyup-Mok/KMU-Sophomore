//
// Created by mokjh on 2023-03-23.
//
#include <iostream>
int t, n;

using namespace std;
int main() {
    cin >> t;
    int num;
    for(int i=0; i<t; i++) {
        int sum = 0;
        cin >> n;
        for(int j=0; j<n; j++) {
            cin >> num;
            sum += num;
        }
        cout << sum << "\n";
    }
}
