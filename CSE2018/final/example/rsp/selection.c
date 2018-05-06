#include "rsp.h"

rsp selection_by_machine(void) {
    return ((rsp) (rand() % 3));
}

rsp selection_by_player(void) {
    char c;
    rsp player_choice;

    printf("Input p, r, or s: ");
    while (isspace(c = getchar()))
        ;
    switch(c) {
        case 'p':
            player_choice = paper;
            break;
        case 'r':
            player_choice = rock;
            break;
        case 's':
            player_choice = scissors;
            break;
        case 'g':
            player_choice = game;
            break;
        case 'i':
            player_choice = instructions;
            break;
        case 'q':
            player_choice = quit;
            break;
        default:
            player_choice = help;
            break;
    }

    return player_choice;
}