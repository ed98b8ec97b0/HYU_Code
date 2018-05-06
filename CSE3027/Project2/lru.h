#include "header.h"

typedef struct packet
{
    char *buffer;
    int length;
    struct packet *next;
} packet;

typedef struct object
{
    // object
    char* url;
    int length;

    struct object *next;
    packet *data;
} object;



typedef struct queue
{
    int size;
    object* front;
    object* back;
} queue;

object *hit(char *url);
int check(char *url);
void miss(object *obje);
void reset_object(object *obje);
void reset_packet(packet *pck);
void reset_queue(queue *cache);
void print_queue();