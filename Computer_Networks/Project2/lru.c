#include "lru.h"

object* hit(queue* cache, char* url, char* path)
{
    object* iter = cache->front;
    object *first, *temp;
    int length;

    // when cache->front is hitted
    if ((strcmp(iter->url, url) == 0) && (strcmp(iter->path, path) == 0))
    {
        first = iter;
        length = first->full_length;
        do
        {
            cache->front = iter->next;
            iter->next = NULL;
            cache->back->next = iter;
            cache->back = iter;
            iter = cache->front;
            length -= iter->length;
        } while (length > 0);
    }
    // when object in midlle of cache is hitted (* also cache->back)
    else
    {
        while(iter->next != NULL)
        {
            if ((strcmp(iter->next->url, url) == 0) && (strcmp(iter->next->path, path) == 0))
            {
                break;
            }
            iter = iter->next;
        }

        first = iter->next;
        length = first->full_length;
        temp = first;
        do
        {
            iter->next = temp->next;
            temp->next = NULL;
            cache->back->next = temp;
            cache->back = temp;
            temp = iter->next;
            length -= iter->length;
        } while (length > 0);
    }

    return first;
}

int check(queue *cache, char *url, char *path)
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
        if (!(strcmp(iter->url, url)) && !(strcmp(iter->path, path)))
        {
            flag = 1;
            break;
        }

        iter = iter->next;
    } while (iter->next != NULL);

    return flag;
}

void miss(queue *cache, object *first)
{
    object *iter, *temp;

    // first input at cache.
    if (cache->size == 0)
    {
        cache->size += first->full_length;
        cache->front = first;

        iter = first;
        while(iter->next != NULL)
        {
            iter = iter->next;
        }
        
        cache->back = iter;

        return;
    }
    
    if (((CASH_SIZE - cache->size) - first->full_length) >= 0)
    {
        cache->size += first->full_length;
        cache->back->next = first;

        iter = first;
        while (iter->next != NULL)
        {
            iter = iter->next;
        }

        cache->back = iter;
    }
    else
    {
        while (((CASH_SIZE - cache->size) - first->full_length) < 0)
        {
            cache->size -= cache->front->full_length;
            
            iter = cache->front;
            while (iter->position != 0)
            {
                temp = iter;
                iter = iter->next;
                free(temp);
            }
            cache->front = iter;
        }
    }
}

void print_cache(queue *cache)
{
    object *iter;

    printf("\n[");

    if (cache->size == 0)
    {
        printf("(null)");
    }
    else
    {
        iter = cache->front;
        while(iter->next != NULL)
        {
            if (iter->position == 0)
            {
                printf("-(%s/%s)-", iter->url, iter->path);
            }   
            iter = iter->next;
        }
    }

    printf("]\n");
}