#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include "smsh.h"

extern char* arr_history[];
extern int arr_index;

int main(void) {
    char path[PATH_SIZE], cmd[CMD_SIZE];
    arr_history[0] = (char *) malloc(sizeof(char) * CMD_SIZE);

    // 구동시 등장하는 쉘 인트로 문구.
    printf("\nSimple Linux Shell\n");
    printf("한양대학교 에리카캠퍼스\n");
    printf("3학년 2학기 시스템프로그래밍\n");
    printf("2015038568 장호연\n\n");

    // 명령어 받아오기.
    while(1) {
        // path 가져오기.
        if (getcwd(path, PATH_SIZE) < 0) {
            perror("getcwd(): ");
            exit(1);
        }

        // command를 받아와 parser에 넣고 argv[]의 형태로 변환.
        printf("%s$ ", path);
        fgets(cmd, CMD_SIZE, stdin);

        // history를 위해 cmd를 가져오는 조건문.
        if (cmd[0] != '!') {
            // history에는 20개까지만 저장.
            if (arr_index <= 20) {
                arr_history[arr_index] = (char *) malloc(sizeof(char) * CMD_SIZE);
                memmove(arr_history[arr_index++], cmd, CMD_SIZE);
            }
            // 0번은 마지막 command를 실행하기 위해 저장하는 구역.
            // 매번 malloc하지 않게 main 최상단에서 malloc을 해둠.
            memmove(arr_history[0], cmd, CMD_SIZE);
        }

        command *parsed = parse_cmd(cmd);
        exec_cmd(parsed->argc, parsed->argv);
    }

    return 0;
}
