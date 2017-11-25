//
//  main.c
//  HW2_MemoryManagement
//
//  Copyright © 2017년 2학기 System Programming. All rights reserved.
//
//  이름 : 장호연
//  학번 : 2015038568
//  학과 : 컴퓨터공학과
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "MemoryManager.h"

void initManager();
void rearrangeBlocks(Block allocatedBlocks[], int freeIndex, int *lastBlockIndex);
void getAllocatedBlocks(Block allocatedBlocks[], int lastIndex);

int main(int argc, const char * argv[]) {
    int which = 0;
    int mallocSize;
    int heapSize;
    int freeIndex;
    Block *block;

    Block allocatedBlocks[100];
    int lastBlockIndex = -1;

    printf("====Memory Manager====\n");
    printf("Set heap size: ");
    scanf("%d", &heapSize);

    initManager(heapSize);

    do {
        printf("\n====================\n");
        getHeap(g_heap);
        printf("Choose a number to operate\n");
        printf("1. malloc\n");
        printf("2. free\n");
        printf("or, exit\n");
        scanf("%d", &which);

        switch(which)
        {
            case 1:
                if (isEmpty(g_heap)) {
                    printf("No memory\n");
                    break;
                }
                printf("Put size for allocating: ");
                scanf("%d", &mallocSize);

                if ((block = m_alloc(mallocSize)) != NULL) { // success allocating
                    allocatedBlocks[++lastBlockIndex] = *block;
                    printf("lastBlockIndex: %d\n", lastBlockIndex);
                    printf("success allocating\n");
                } else { // fail allocating
                    printf("fail allocating\n");
                }
                break;
            case 2:
                if (lastBlockIndex < 0) {
                    printf("No blocks\n");
                    break;
                }
                getAllocatedBlocks(allocatedBlocks, lastBlockIndex);
                do {
                    printf("Which block will be free (index 0~%d) : ", lastBlockIndex);
                    scanf("%d", &freeIndex);
                } while(freeIndex > lastBlockIndex || freeIndex < 0);
                if (freeIndex <= lastBlockIndex) {
                    m_free(allocatedBlocks[freeIndex]);
                    rearrangeBlocks(allocatedBlocks, freeIndex, &lastBlockIndex); // fill the empty space
                }
                break;
        }
    } while(which == 1 || which == 2);

    printf("====exit====\n");

    return 0;
}

/*
 * init Manager like constructor
 */
void initManager(int heapSize) {
    Block headerBlock;
    Block trailerBlock;
    Block heapMemoryBlock;

    g_heap = ((DLinkedList*) malloc(sizeof(DLinkedList)));
    Node *headerNode = (Node*) malloc(sizeof(Node));
    headerBlock.start = 0;
    headerBlock.size = heapSize;
    headerBlock.end = heapSize - 1;
    Node *trailerNode = (Node*) malloc(sizeof(Node));
    trailerBlock.start = 0;
    trailerBlock.size = heapSize;
    trailerBlock.end = heapSize - 1;
    Node *heapMemory = (Node*) malloc(sizeof(Node));
    heapMemoryBlock.start = 0;
    heapMemoryBlock.size = heapSize;
    heapMemoryBlock.end = heapSize - 1;

    g_heap->size = 0;
    g_heap->header = headerNode;
    g_heap->trailer = trailerNode;

    g_heap->header->next = g_heap->trailer;
    g_heap->trailer->prev = g_heap->header;

    setHeaderInfo(g_heap, headerBlock);
    setTrailerInfo(g_heap, trailerBlock);
    heapMemory->item = heapMemoryBlock;

    addFirst(g_heap, heapMemory);
    addfreeBlock(heapMemoryBlock);
}

void rearrangeBlocks(Block allocatedBlocks[], int freeIndex, int *lastBlockIndex) {
    int i = freeIndex;
    while (i <= *lastBlockIndex) {
        allocatedBlocks[i] = allocatedBlocks[i+1];
        i++;
    }
    (*lastBlockIndex)--;
}

void getAllocatedBlocks(Block allocatedBlocks[], int lastIndex) {
    int i = 0;
    while (i != lastIndex+1) {
        printf("%d): [%d<-(%d)->%d]\n", i, allocatedBlocks[i].start, allocatedBlocks[i].size, allocatedBlocks[i].end);
        i++;
    }
}
