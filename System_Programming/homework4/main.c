#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include "smsh.h"

extern char* arr_history[];
extern int arr_index;

int main(void) {
    char path[PATH_SIZE], cmd[CMD_SIZE];
    char *bin_exec;
    int pid, n;

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
        if (arr_index <= 20) {
            arr_history[arr_index] = (char *) malloc(sizeof(char) * CMD_SIZE);
            memmove(arr_history[arr_index++], cmd, CMD_SIZE);
        }
        command *parsed = parse_cmd(cmd);


        // argv[0]에 담겨있는 command에 맞춰 필요한 함수 실행.
        // 종료 command.
        if (strcmp(parsed->argv[0], "exit") == 0) {
            printf("BYE!\n");
            exit(0);
        }
        // cd command.
        if (strcmp(parsed->argv[0], "cd") == 0) {
            cd(parsed->argc, parsed->argv);
            continue;
        }
        // history command.
        if (strcmp(parsed->argv[0], "history") == 0) {
            pid = fork();
            switch(pid) {
                case -1:
                    perror("fork(): ");
                    break;
                case 0:
                    history(parsed->argc, parsed->argv);
                    exit(5);
                    break;
                default:
                    pause();
                    break;
            }
            continue;
        }
        // !n command.
        if (strncmp(parsed->argv[0], "!", 1) == 0) {
            continue;
        }

        // in shell fuction이 아니라 /bin안에 들어있는 함수. 여기에마저 없다면 command not found 메시지를 띄운다.
        bin_exec = (char *)malloc(sizeof(char) * (6 + strlen(parsed->argv[0])));
        strcpy(bin_exec, "/bin/");
        strcat(bin_exec, parsed->argv[0]);
        pid = fork();
        switch(pid) {
            case -1:
                perror("fork(): ");
                break;
            case 0:
                n = execv(bin_exec, parsed->argv);
                if (n < 0) {
                    printf("smsh: command not found: %s\n", parsed->argv[0]);
                }
                exit(5);
                break;
            default:
                pause();
                break;
        }

    }

    return 0;
}
