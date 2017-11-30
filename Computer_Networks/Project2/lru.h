#include "header.h"

typedef struct object
{
    // object
    char url[URL_SIZE];
    char path[PATH_SIZE];
    int length;
    int full_length;
    int size;
    char buffer[BUFF_SIZE];

    // queue
    struct object* next;
} object;

typedef struct
{
    int size;
    object* front;
    object* back;
} queue;

object *hit(queue *cache, char *url, char *path);
int check(queue *cache, char *url, char *path);
void header_move(queue *cache, int n);
void miss(queue *cache, object *obje);
void print_cache(queue *cache);