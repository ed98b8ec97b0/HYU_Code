#include "lru.h"

object* hit(char* url, char* path)
{
    object* iter = cache->front;
    do
    {
        if ((strcmp(iter->url, url) == 0) && (strcmp(iter->path, path) == 0))
        {
            break;
        }

        iter = iter->next;
    } while (iter->next != NULL);  

    return iter;
}

int check(char* url, char* path)
{
    int flag = 0; // 1 = hit, 0 = miss.

    // if cache is empty, then return miss.
    if (cache->size == 0)
    {
        return flag;
    }

    // search node in cache which 
    object* iter = cache->front;
    do
    {
        if ((strcmp(iter->url, url) == 0) && (strcmp(iter->path, path) == 0))
        {
            flag = 1;
            break;
        }

        iter = iter->next;
    } while (iter->next != NULL);

    return flag;
}

void header_move(int n)
{
    for (int i = 0; i < n; i++)
    {
        if (cache->header->next != NULL)
        {
            cache->header = cache->header->next;
        }
        else
        {
            cache->header = cache->front;
        }
    }
}

void miss(object* obje, int n)
{
    object* prev = cache->header;
    object* temp1 = prev->next;
    object* temp2 = temp1->next;

    for(int i = 0; i < n; i++)
    {
        free(temp1);
        temp1 = temp2;
        temp2 = temp1->next;
    }

    prev->next = obje;
    header_move(n);
}