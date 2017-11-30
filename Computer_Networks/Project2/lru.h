#include <string.h>

#define CASH_SIZE 6164480

typedef struct object
{
    // object
    char* url;
    char* path;
    int length;
    int count;
    char* buffer;

    // queue
    object* next;
} object

typedef struct queue
{
    int size;
    object* front;
    object* back;
    object* header;
} queue

queue* cache = (queue*)malloc(sizeof(queue));