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

