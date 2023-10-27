#include <iostream>
#include <ncurses.h>
#include <cstdlib>
#include <queue>
#include <ctime>
#include <fstream>

#include "snake_game.hpp"
#include "snake.hpp"

int main(void) {
    setlocale(LC_ALL, "");
    int game;
    while(1) {
        game=show_menu();
        switch(game) {
            case 1:
                classic_game();
                break;
            case 2:
                endwin();
                return 0;
        }
    }
}
