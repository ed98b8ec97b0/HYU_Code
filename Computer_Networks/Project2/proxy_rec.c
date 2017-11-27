#include "proxy.h"

#define GET "GET"
#define HTTP "HTTP/1.1"

item proxy_rec(item req_item) {
    int sockfd, n;
    char *buffer[BUFF_SIZE];
    struct sockaddr_in serv_addr;
    struct hostent *server;

    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) {
        error("ERROR socket");
    }
    server = gethostbyname(req_item.url);
    if (server == NULL) {
        fprintf(stderr, "ERROR gethostbyname");
        exit(0);
    }
    memset((char *) buffer, 0, BUFF_SIZE);
    serv_addr.sin_family = AF_INET;
    memmove((char *)&serv_addr.sin_addr.s_addr, (char *) server->h_addr, server->h_length);
    serv_addr.sin_port = 80;

    n = connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr));
    if (n < 0) {
        error("ERROR connect");
    }
    
    char* header = (char *) malloc((strlen(req_item.url) + strlen(GET) + strlen(HTTP) + 2) * sizeof(char));
    memmove(buffer, header, strlen(header));
    n = write(sockfd, buffer, strlen(buffer));
    if (n < 0) {
        error("ERROR write");
    }

    n = read(sockfd, buffer, strlen(buffer));
    if (n < 0) {
        error("ERROR write");
    }
    
}