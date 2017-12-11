#include <stdio.h>
#include <stdlib.h>

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
    int n;

    // argument 수가 1개 이상인 경우.
    if (argc != 1) {
        printf("usage: !# (1 <= # <= 20)\n");
        return;
    }

    // string을 int로 변경.
    n = atoi(argv[0] + 1);

    // int의 범위가 1부터 20사이인지 확인.
    if (n < 1 || n > 20) {
        printf("usage: !# (1 <= # <= 20)\n");
        return;
    }

    // 해당 커맨드 가져와서 parse_cmd에 넣은 뒤 exec_cmd에 넣어 실행.
    command *recall = (command *)malloc(sizeof(command));
    recall = parse_cmd(arr_history[n]);
    exec_cmd(recall->argc, recall->argv);
}
