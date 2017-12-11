#include <stdlib.h>
#include <string.h>

#include "smsh.h"

command* parse_redir(char* cmd) {
    command* output = (command *)malloc(sizeof(command));
    char *a, *b, *c, *d;

    // redirection 위치 찾기.
    // 여러개가 나올 때 어떻게 처리할 지 결정할 것.
    a = strstr(cmd, ">");
    b = strstr(cmd, "<");
    c = strstr(cmd, ">>");
    d = strstr(cmd, ">!");

    // strtok로 나눠서 커맨드부분은 parse_cmd에 넣어서 따로 처리.
    // redirection 부분은 맞게 dup2를 이용하여 fork단에서 처리.

    return output;
}
