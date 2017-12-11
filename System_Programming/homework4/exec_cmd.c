#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include "smsh.h"

void exec_cmd(int argc, char* argv[]) {
    char *bin_exec;
    int pid, n;

    // argv[0]에 담겨있는 command에 맞춰 필요한 함수 실행.
    // 종료 command.
    if (strcmp(argv[0], "exit") == 0) {
        printf("BYE!\n");
        exit(0);
    }
    // cd command.
    if (strcmp(argv[0], "cd") == 0) {
        cd(argc, argv);
        return;
    }
    // history command.
    if (strcmp(argv[0], "history") == 0) {
        pid = fork();
        switch(pid) {
            case -1:
                perror("fork(): ");
                break;
            case 0:
                history(argc, argv);
                exit(5);
                break;
            default:
                pause();
                break;
        }
        return;
    }
    // !n command.
    if (strncmp(argv[0], "!", 1) == 0) {
        pid = fork();
        switch(pid) {
            case -1:
                perror("fork(): ");
                break;
            case 0:
                history_call(argc, argv);
                exit(5);
                break;
            default:
                pause();
                break;
        }
        return;
    }

    // in shell fuction이 아니라 /bin안에 들어있는 함수. 여기에마저 없다면 command not found 메시지를 띄운다.
    bin_exec = (char *)malloc(sizeof(char) * (6 + strlen(argv[0])));
    strcpy(bin_exec, "/bin/");
    strcat(bin_exec, argv[0]);
    pid = fork();
    switch(pid) {
        case -1:
            perror("fork(): ");
            break;
        case 0:
            n = execv(bin_exec, argv);
            if (n < 0) {
                printf("smsh: command not found: %s\n", argv[0]);
            }
            exit(5);
            break;
        default:
            pause();
            break;
    }
}
