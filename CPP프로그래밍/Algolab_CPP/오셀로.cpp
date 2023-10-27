#include <iostream>
using namespace std;

char otl[9][9];
int dr[8][2] = {{-1, -1},{0, -1},{1, -1},{1, 0},{1, 1},{0, 1},{-1, 1},{-1, 0}};

int main() {
    int size = 8;
    int t;
    cin >> t;
    while (t--) {
        for(int x = 1; x <= size; x++) {
            for(int y = 1; y <= size; y++) {
                otl[x][y] = '+';
            }
        }   
        otl[4][4] = 'O';
        otl[5][5] = 'O';
        otl[4][5] = 'X';
        otl[5][4] = 'X';

        int cnt;
        cin >> cnt;
        int white = 0, black = 0;
        
        char o_x;
        o_x = 'X';
        while (cnt--) {
            int i,j;
            cin >> i;
            cin >> j;
            otl[i][j] = o_x;
            
            for(int d = 0; d < 8; d++){
                int cnt_ch = 0;
                int mv_x = i;
                int mv_y = j;
                while(true){
                    mv_x += dr[d][0];
                    mv_y += dr[d][1];
                    
                    if(mv_x >= 9 || mv_y >= 9 || mv_x <= 0 || mv_y <= 0) {
                        cnt_ch = 0;
                        break;   
                    }
                    else if(otl[mv_x][mv_y] == '+'){
                        cnt_ch = 0;
                        break;
                    }

                    else if(otl[mv_x][mv_y] == o_x){break;} 
                    else{cnt_ch++;}
                }
                for(int c = 1; c <= cnt_ch; c++){
                    int i_ = i + dr[d][0] * c;
                    int j_ = j + dr[d][1] * c;
                    if(i_ >= 9 || j_ >= 9 || i_ <= 0 || j_ <= 0) {
                        cnt_ch = 0;
                        break;   
                    }
                    otl[i_][j_] = o_x;
                }
            }
            if(o_x == 'X') o_x = 'O';
            else if(o_x == 'O') o_x = 'X';   
        }
        for(int x = 1; x <= size; x++){
            for(int y = 1; y <= size; y++){
                if(otl[x][y] == 'X') black++;
                if(otl[x][y] == 'O') white++;       
            }
        }
        cout << black << " " << white << "\n";
        for(int x = 1; x <= size; x++){
            for(int y = 1; y <= size; y++){
                cout << otl[x][y];
            }
            cout << "\n";
        }
    }
}