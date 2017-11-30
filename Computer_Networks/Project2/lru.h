#include "header.h"

typedef struct object
{
    // object
    char *url;
    char *path;
    int length;
    int full_length;
    int size;
    char *buffer;

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