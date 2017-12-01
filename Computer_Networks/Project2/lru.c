#include "lru.h"

extern queue *cache;

void reset_object(object *obje)
{
    obje->url = (char *)malloc(sizeof(char) * URL_SIZE);
    memset(obje->url, 0, URL_SIZE);
    obje->length = 0;
    obje->next = NULL;
    obje->data = NULL;
}

void reset_packet(packet *pck)
{
    pck->buffer = (char *)malloc(sizeof(char) * BUFF_SIZE);
    memset(pck->buffer, 0, BUFF_SIZE);
    pck->next = NULL;
    pck->length = 0;
}

void reset_queue(queue *cache)
{
    cache->size = 0;
    cache->front = NULL;
    cache->back = NULL;
}

object* hit(char* url)
{
    int flag = 0;
    object *obje = cache->front, *temp;

    while (obje != NULL)
    {
        if (!(strcmp(obje->url, url)))
        {
            break;
        }
        temp = obje;
        obje = obje->next;
        flag = 1;
    }
    if (flag == 0)
    {
        cache->front = obje->next;
        cache->back->next = obje;
        cache->back = obje;
        obje->next = NULL;
    }
    else
    {
        if (obje->next != NULL)
        {
            temp->next = obje->next;
            obje->next = NULL;
            cache->back->next = obje;
            cache->back = obje;
        }
    }
    

    return obje;
}

int check(char *url)
{
    int flag = 0; // 1 = hit, 0 = miss.
    // if cache is empty, then return miss.
    if (cache->size == 0)
    {
        return flag;
    }
    
    // search node in cache which 
    object* iter = cache->front;
    while (iter != NULL)
    {
        if (!(strcmp(iter->url, url)))
        {
            flag = 1;
            break;
        }

        iter = iter->next;
    }

    return flag;
}

void miss(object *obje)
{
    // queue size = 0
    if (cache->size == 0)
    {
       cache->size += obje->length;
       cache->front = obje;
       cache->back = obje;
    }
    else
    {
        if (((CASH_SIZE - cache->size) - obje->length) >= 0)
        {
            cache->size += obje->length;
            cache->back->next = obje;
            cache->back = obje;
        }
        else
        {
            while (((CASH_SIZE - cache->size) - obje->length) < 0)
            {
                cache->size -= cache->front->length;

                packet *pck = obje->data;
                packet *temp = obje->data;
                while (pck != NULL)
                {
                    pck = pck->next;
                    free(temp);
                }

                object *temp2 = cache->front;
                cache->front = cache->front->next;
                free(temp2);
            }


        }
    }    
}

void print_queue()
{
    object *iter = cache->front;

    printf("\nCACHE SIZE = %d / 5242880\n", cache->size);
    while (iter != NULL)
    {
        printf("[%s, %d]\n", iter->url, iter->length);
        iter = iter->next;
    }
}