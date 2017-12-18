#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "smsh.h"

command* parse_cmd(char *cmd) {
    char *tmp = NULL;
    command *output = (command *)malloc(sizeof(command));
    output->argc = 0;
    output->bg_flag = 0;

    //맨 마지막에 bg가 있는지 확인.
    if (cmd[strlen(cmd) - 2] == '&') {
        output->bg_flag = 1;
        // 마지막에 &가 있다면 bg_flag를 1로 바꾸고 &을 null로 바꿔 일반적으로 사용할 수 있게 만든다.
        cmd[strlen(cmd) - 2] = '\0';
    }

    //tokenizing
    tmp = strtok(cmd, " \n");
    while (tmp != NULL) {
        output->argv[output->argc] = tmp;
        output->argc++;
        tmp = strtok(NULL, " \n");
    }

    // argument가 한개일 경우 마지막이 \n이 아니라 null이 들어가도록 하는것.
    if (output->argc == 1) {
        tmp = strtok(output->argv[0], "\0");
        output->argv[0] = tmp;
    }

    return output;
}
