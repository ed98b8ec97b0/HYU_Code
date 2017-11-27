#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <strings.h>
#include <unistd.h>
#include <fcntl.h>
#include <netdb.h>

#define BACKLOG 5
#define BUFF_SIZE 5120
#define CASH_SIZE 1024

void error(char *msg) {
    perror(msg);
    exit(1);
}

typedef struct {
    char* req;
    char* url;
    char* ver;
    uint32_t ip_addr;
    int port_num;
} item;

item proxy_req(int);
item proxy_rec(item);