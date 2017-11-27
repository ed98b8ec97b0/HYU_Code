#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <strings.h>
#include <unistd.h>
#include <fcntl.h>

#define BACKLOG 5
#define BUFF_SIZE 5120
#define CASH_SIZE 10

void error(char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char* argv[]) {
    int proxy_sock, serv_sock, cli_sock;
    int port_no;
    int n;
    char buffer[BUFF_SIZE];
    struct sockaddr_in proxy_addr, serv_addr, cli_addr;
    socklen_t clilen;

    if (argc < 2) {
        fprintf(stderr, "ERROR argument");
        exit(1);
    }

    // proxy server socket open
    proxy_sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (proxy_sock < 0) {
        error("ERROR proxy socket");
    }

    // reset proxy_addr
    memset((char *) &proxy_addr, 0, sizeof(proxy_addr));
    port_no = atoi(argv[1]);
    proxy_addr.sin_family = AF_INET;
    proxy_addr.sin_addr.s_addr = INADDR_ANY;
    proxy_addr.sin_port = htons(port_no);

    // binding proxy socket
    n = bind(proxy_sock, (struct sockaddr *) & proxy_addr, sizeof(proxy_addr));
    if (n < 0) {
        error("ERROR bind proxy socket");
    }

    // proxy socket into listening mode 
    n = listen(proxy_sock, BACKLOG);
    if (n < 0) {
        error("ERROR listen proxy socket");
    }

    while(1) {
        clilen = sizeof(client_address);
        
    }
}