#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <netdb.h>
#include <ctype.h>
#include <signal.h>
#include <errno.h>

#define BACKLOG 5
#define BUFF_SIZE 512
#define HTTP_PROT "HTTP://"
#define HTTP_VER0 "HTTP/1.0"
#define HTTP_VER1 "HTTP/1.1"
#define NOT_FOUND "HTTP/1.1 404 NOT Found\r\n\r\n"
#define BAD_REQUEST "HTTP/1.1 400 Bad Request\r\n\r\n"
#define URL_SIZE 500
#define PATH_SIZE 500
#define OBJE_SIZE 524288
#define CASH_SIZE 5242880