#include "rsp.h"

void prn_final_status(int win_cnt, int lose_cnt) {
    if (win_cnt > lose_cnt)
        printf("CONGRATUATIONS - You WIN!\n\n");
    else if (win_cnt == lose_cnt)
        printf("A DRAW - You tied!\n\n");
    else
        printf("SORRY - You lost!\n\n");
}

void prn_game_status(int win_cnt, int lose_cnt, int tie_cnt) {
    printf("\n%s\n%s%4d\n%s%4d\n%s%4d\n%s%4d\n\n",
        "GAME STATUS",
        "   Win:   ", win_cnt,
        "   Lose:  ", lose_cnt,
        "   Tie:   ", tie_cnt,
        "   Total: ", win_cnt + lose_cnt + tie_cnt);
}

void prn_help(void) {
    printf("\n%s\n",
        "The foloowing characters can be used for input:\n"
        "       p for paper\n"
        "       r for rock\n"
        "       s for scissors\n"
        "       g print the game status\n"
        "       h help, print this list\n"
        "       i reprint the instructions\n"
        "       q quit this game\n");
}

void prn_instructions(void) {
    printf("\n%s\n",
        "PAPER, ROCK, SCISSORS:\n"
        "   In this game p is for \"paper,\" r is for \"rock,\" and "
        "s is for \"scissors.\"\n"
        "   Both the player and the machine\n"
        "   will choose one of p, r, or s"
        "   If the tow chosices are the same,\n"
        "   then the game is a tie. Otherwise:\n"
        "       \"paper covers the rock\" (a win for paper),\n"
        "       \"rock breaks the scissors\" (a win for rock),\n"
        "       \"scissors cut the paper\" (a win for scissors),\n"
        "\n"
        "   There are other aloowable inputs:\n"
        "       g for game stutus\n"
        "       h for help\n"
        "       i for instructions\n"
        "       q for quit\n"
        "\n"
        "   This game is played repeatedly until q is entered.\n"
        "\n"
        "   Good luck\n");
}