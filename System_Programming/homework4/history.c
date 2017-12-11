#include <stdio.h>

#include "smsh.h"

char* arr_history[21];
int arr_index = 1;

// history 전체를 띄우는 함수.
void history(int argc, char* argv[]) {
    if (argc != 1) {
        printf("usage: history\n");
        return;
    }
    for (int i = 1; i < arr_index; i++) {
        printf(" %2d  %s", i, arr_history[i]);
    }
    printf("\n");
}

// history 안에 들어있던 함수를 가져오는 함수.
void history_call(int argc, char* argv[]) {
    if (argc != 1) {
        printf("usage: !# (1 <= # <= 20)\n");
        return;
    }
}
