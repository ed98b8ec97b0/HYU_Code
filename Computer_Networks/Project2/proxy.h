#include <netinet/in.h>
#include <stdlib.h>
#include <strings.h>
#include <unistd.h>
#include <fcntl.h>

#define BACKLOG 5
#define BUFF_SIZE 1024

void error(char *msg) {
    perror(msg);
    exit(1);
}