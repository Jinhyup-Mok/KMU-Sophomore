#include <queue>
#include <cstdlib>
#include <ncurses.h>
#include <locale.h>
#include <ctime>
#include <fstream>
#include <iostream>

#include "snake_game.hpp"
#include "snake.hpp"
#include "map.hpp"

using namespace std;

Snake::Snake(int s) : len(DEF_LEN_CLASSIC), stage(s)
{
    int i;
    Cell temp;

    // Scoreboard 초기화
    growth = 0;
    poison = 0;
    gate = 0;

    // Wall 설정
    setWall(stage);

    // time 초기화
    item_starttime = 0;
    gate_starttime = 0;
    
    fdir=RIGHT;
    temp.p=rand_point(cells, items, walls);

    // Snake길이때문에 화면을 넘어가는 경우 예외처리
    Point tmpP = temp.p;
    tmpP.col += DEF_LEN_CLASSIC;

    if((temp.p.col+DEF_LEN_CLASSIC) >= MAX_ROW-1){
        temp.p.col -= (MAX_ROW-DEF_LEN_CLASSIC);
    }else if(isWall(tmpP)){
        temp.p.col -= MAX_ROW-DEF_LEN_CLASSIC;
    }

    for(i=0; i<len-1; i++) {
        temp.p.col+=i;
        if(temp.p.col+i >= MAX_COL-1){
            temp.p.col=MAX_COL-i-1;
        }
        cells.push_front(temp);
    }

    temp.p.col+=i;
    cells.push_front(temp);

    // 랜덤으로 아이템생성
    makeItem();
    coll=0;
}



int Snake::getfdir()
{
    return fdir;
}

void Snake::setfdir(int dir)
{
    fdir=dir;
}

void Snake::createNode(int d)
{
    fdir=d;
}

void Snake::makeItem()
{
    item_starttime = time(NULL);
    srand(time(NULL));

    items.clear();
    Item tempItem;
    int cnt = (rand()%MAX_ITEM)+1;

    for(int i=0; i<cnt; i++){
        tempItem.p = rand_point(cells, items, walls);
        tempItem.points = rand_score();
        items.push_back(tempItem);
    }
    curitem.itemFlag = 0;
}

void Snake::eatItem()
{
    items.erase(curitem.t);
}

void Snake::makeGate()
{
    //snake가 gate 통과중인지 check
    for(deque<Cell>::iterator snake=cells.begin(); snake!=cells.end(); ++snake){
        for(deque<Cell>::iterator gate=gates.begin(); gate!=gates.end(); ++gate){
            if(snake->p.row==gate->p.row && snake->p.col==gate->p.col) {
                return;
            }
        }
    }

    gates.clear();
    gate_starttime = time(NULL);

    // WALL에서 GATE를 두개 생성.
    for(int i=0; i<2; i++){
        Cell temp;
        temp.p = rand_point(cells, items, walls, gates);
        gates.push_back(temp);
    }

}

void Snake::movesnake()
{
    Point f, b;
    Cell temp;
    f=cells.front().p;
    b=cells.back().p;

    switch(fdir) {
        case UP:
            f.row-=1;
            break;
        case DOWN:
            f.row+=1;
            break;
        case RIGHT:
            f.col+=1;
            break;
        case LEFT:
            f.col-=1;
            break;
    }
    temp.p=f;
    
    // 충돌시 게임 종료
    if(wallcollid() || collide()){
        coll=1;
        return;
    }

    cells.push_front(temp);

    bool itemFlag = false;
    for(deque<Item>::iterator it=items.begin(); it!=items.end(); ++it){
        if(cells.front().p.row==it->p.row && cells.front().p.col==it->p.col) { //snake머리랑 아이템이랑 위치 같으면
            itemFlag = true;

            curitem.t = it;
            curitem.p.row = it->p.row;
            curitem.p.col = it->p.col;
            curitem.itemFlag = it->points;
            break;
        }
    }

    // Gate통과 Check
    bool gateFlag = false;
    Point gateP;    // 반대 Gate

    for(deque<Cell>::iterator gate=gates.begin(); gate!=gates.end(); ++gate){
        if(cells.front().p.row==gate->p.row && cells.front().p.col==gate->p.col) {
            gateFlag = true;
        }else{
            gateP = gate->p;
        }
    }
    

    // 1) Growth Item을 획득 : 몸의 길이가 1 증가.
    // 2) Poison Item을 획득 : 몸의 길이는 감소(꼬리부분이 감소).
    // Snake가 움직일 때마다, Node 추가
    if(itemFlag){
        if(curitem.itemFlag == -1){    //Poison Item
            cells.pop_back();
            cells.pop_back();
            poison++;
        }else if(curitem.itemFlag == 1){    //Growth Item
            growth++;
        }
        eatItem();
    }else{
        cells.pop_back();
    }

    // 5초 지나면 아이템 자동 생성.
    time_t cur_time;
    cur_time = time(NULL);

    if(cur_time-item_starttime >= ITEM_TIME){
        makeItem();
    }

    // Snake길이가 6이상일때 gate출현
    if(gateFlag){
        Cell temp;
        temp.p = gateP;

        // 진입 gate가 가장자리면
        if(gateP.row == 0){
            fdir = DOWN;
        }else if(gateP.row == MAX_ROW-1){
            fdir = UP;
        }else if(gateP.col == 0){
            fdir = RIGHT;
        }else if(gateP.col == MAX_COL-1){
            fdir = LEFT;
        }else{
        
            // 진입할 방향이 Wall 인지 확인
            for(int i=0; i<4; i++){
                Point tmp = gateP;
                switch(fdir) {
                    case UP:
                        tmp.row-=1;
                        break;
                    case DOWN:
                        tmp.row+=1;
                        break;
                    case RIGHT:
                        tmp.col+=1;
                        break;
                    case LEFT:
                        tmp.col-=1;
                        break;
                }

                // 벽이 아니면 진행방향으로.
                if(!isWall(tmp)) break;

                // 1) 진행방향과 반대
                // 2) 그다음부턴 시계방향
                if(i==0) fdir = (fdir+2)%4;
                else fdir = (fdir+1)%4;
            }
        }

        cells.push_front(temp);
        cells.pop_back();
        gate++;

    }else{    // 벽을 통과하고 있지 않았을 때
        if(cells.size() >= APPEAR_GATE_LEN && (cur_time-gate_starttime >= GATE_TIME || gate_starttime==0)){
            makeGate();
        }
    }
    
}

void Snake::render()
{
    erase();
    
    for(deque<Cell>::iterator it=cells.begin(); it!=cells.end(); ++it){
        if(it==cells.begin()){
            attron(COLOR_PAIR(COLOR_SNAKE_HEAD));
            mvprintw(it->p.row, it->p.col, "\u2B1B");  //\u2B1C
            attroff(COLOR_PAIR(COLOR_SNAKE_HEAD));
        }else{
            attron(COLOR_PAIR(COLOR_SNAKE_BODY));
            mvprintw(it->p.row, it->p.col, "\u2B1B");
            attroff(COLOR_PAIR(COLOR_SNAKE_BODY));
        }
    }
    
    // item
    for(deque<Item>::iterator it=items.begin(); it!=items.end(); ++it){
        if(it->points == 1){
            attron(COLOR_PAIR(COLOR_ITEM_GROWTH));
            mvprintw(it->p.row, it->p.col, "\u2B1B");
            attroff(COLOR_PAIR(COLOR_ITEM_GROWTH));
        }else if(it->points == -1){
            attron(COLOR_PAIR(COLOR_ITEM_POISON));
            mvprintw(it->p.row, it->p.col, "\u2B1B");
            attroff(COLOR_PAIR(COLOR_ITEM_POISON));
        }
    }

    // Wall
    for(deque<Cell>::iterator it=walls.begin(); it!=walls.end(); ++it){
        if(it->type == IMMUNEWALL){
            attron(COLOR_PAIR(COLOR_IMMUNEWALL));
            mvprintw(it->p.row, it->p.col, "\u2B1B");
            attroff(COLOR_PAIR(COLOR_IMMUNEWALL));
        }else if(it->type == WALL){
            attron(COLOR_PAIR(COLOR_WALL));
            mvprintw(it->p.row, it->p.col, "\u2B1B");
            attroff(COLOR_PAIR(COLOR_WALL));
        }
    }

    // Gate
    for(deque<Cell>::iterator it=gates.begin(); it!=gates.end(); ++it){
        attron(COLOR_PAIR(COLOR_GATE));
        mvprintw(it->p.row, it->p.col, "\u2B1B");
        attroff(COLOR_PAIR(COLOR_GATE));
    }
    refresh();
}

// Snake Head가 자신의 Body에 부딪혔을 경우
int Snake::collide() {
    for(deque<Cell>::iterator it=cells.begin(); it!=cells.end(); ++it){
        if(cells.front().p.row==it->p.row && cells.front().p.col==it->p.col && it!=cells.begin()) {
            return 1;
        }
    }
    return 0;
}

// Snake Head가 Wall에 부딪혔을 경우
int Snake::wallcollid()
{
    if(isWall(cells.front().p))
        return 1;

    return 0;
}


// 기본 Wall Setting
// 1) 모서리는 Gate로 변할 수 없어서 IMMUNEWALL로 고정
// 2) WALL은 Gate로 변할 수 있음.
void Snake::setWall(int stage)
{
    walls = setStageWall(stage);
}


int Snake::getcoll()
{
    return coll;
}

int Snake::getscore()
{
    return cells.size();
}

int Snake::isWall(Point p)
{
    for(deque<Cell>::iterator it=gates.begin(); it!=gates.end(); ++it){
        if(p.row==it->p.row && p.col==it->p.col){
            return 0;
        }
    }

    for(deque<Cell>::iterator it=walls.begin(); it!=walls.end(); ++it){
        if(p.row==it->p.row && p.col==it->p.col){
            return 1;
        }
    }

    return 0;
}

int Snake::getCntGrowth()
{
    return growth;
}

int Snake::getCntPoison()
{
    return poison;
}

int Snake::getCntGate()
{
    return gate;
}

int Snake::getStage()
{
    return stage;
}
