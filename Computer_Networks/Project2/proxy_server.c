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
#define CASH_SIZE 10
#define NOT_FOUND "HTTP/1.1 404 NOT Found\r\n\r\n"

void error(char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char* argv[]) {
    int proxy_sock, cli_sock;
    int port_no;
    int n;
    char buffer[BUFF_SIZE], tmp[BUFF_SIZE];
    char *token = NULL, *url = NULL;
    struct sockaddr_in proxy_addr, serv_addr, cli_addr;
    struct hostent *server;
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
        clilen = sizeof(cli_addr);

        // accepting client socket
        cli_sock = accept(proxy_sock, (struct sockaddr *) &proxy_addr, &clilen);
        if (cli_sock < 0) {
            error("ERROR accept client socket");
        }

        // reset buffer
        memset(buffer, 0, BUFF_SIZE);

        // read client socket
        n = read(cli_sock, buffer, BUFF_SIZE-1);
        if (n < 0) {
            error("ERROR read client socket");
        }

        // get url from http request
        memcpy(tmp, buffer, BUFF_SIZE);
        token = strtok(tmp, " ");
        url = strtok(NULL, " ");

        
        // connet to server
        server = gethostbyname(url);
        if (server == NULL) {
            n = write(cli_sock, NOT_FOUND, 27);
            if (n < 0) {
                error("ERROR write client socket");
            }
        }
        memset((char *) &serv_addr, 0, sizeof(serv_addr));
        serv_addr.sin_family = AF_INET;
        memcpy((char *) &serv_addr.sin_addr.s_addr, (char *) server->h_addr, server->h_length);
        serv_addr.sin_port = 80;

        n = connect(proxy_sock, (struct sockaddr *) &serv_addr, sizeof(serv_addr));
        if (n < 0) {
            error("ERROR connect server");
        }
        
        n = write(proxy_sock, buffer, BUFF_SIZE);
        if (n < 0) {
            error("ERROR write proxy");
        }

        n = read(proxy_sock, buffer, BUFF_SIZE);
        if (n < 0) {
            error("ERROR read proxy");
        }

        n = write(cli_sock, buffer, BUFF_SIZE);
        if (n < 0) {
            error("ERROR wrtie client socket");
        }

        memset(buffer, 0, BUFF_SIZE);
        close(cli_sock);
        close(proxy_sock);
    }

    return 0;
}