#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <netdb.h>

#define BACKLOG 5
#define BUFF_SIZE 512
#define NOT_FOUND "HTTP/1.1 404 NOT Found\r\n\r\n"
#define URL_SIZE 300
#define PATH_SIZE 300
#define OBJE_SIZE 524288
#define CASH_SIZE 5242880