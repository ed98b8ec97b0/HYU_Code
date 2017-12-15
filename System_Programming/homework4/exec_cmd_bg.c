#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

#include "smsh.h"

void exec_cmd_bg(int argc, char* argv[]) {
    char *bin_exec;
    int pid, n;

    // shell에서 따로 fork가 필요없이 작동하는 command
    // exit command
    if (strcmp(argv[0], "exit") == 0) {
        printf("BYE!\n");
        exit(0);
    }
    // cd command.
    if (strcmp(argv[0], "cd") == 0) {
        cd(argc, argv);
        return;
    }

    // command 실행을 위한 fork
    pid = fork();
    if (pid < 0) {
        perror("fork(): ");
    }
    else if (pid == 0) {
        // history command
        if (strcmp(argv[0], "history") == 0) {
            history(argc, argv);
        }
        // !# command
        else if (strncmp(argv[0], "!", 1) == 0) {
            history_call(argc, argv);
        }
        else {
            // in shell fuction이 아니라 /bin안에 들어있는 함수.
            // 여기에마저 없다면 command not found 메시지를 띄운다.
            bin_exec = (char *)malloc(sizeof(char) * (6 + strlen(argv[0])));
            strcpy(bin_exec, "/bin/");
            strcat(bin_exec, argv[0]);
            n = execv(bin_exec, argv);
            if (n < 0) {
                printf("smsh: command not found: %s\n", argv[0]);
            }
        }

        // 부모 프로세스에게 종료되었다고 알림.
        exit(5);
    }
    else {
    }
}
