//
//  MemoryManager.c
//  HW2_MemoryManagement
//
//  Copyright © 2017년 2학기 System Programming. All rights reserved.
//
//  이름 : 장호연
//  학번 : 2015038568
//  학과 : 컴퓨터공학과
//

#ifndef _MemoryManager_H_
#define _MemoryManager_H_

#include <stdio.h>
#include "DLinkedList.h"

Block *m_alloc(int size);
void m_free(Block block);
void getHeap(DLinkedList *heap);
Node* getfreeBlock(Block freeBlock[], int size);
void addfreeBlock(Block block);
void delfreeBlock(Block block);
void rearrangefreeBlock();
void printfreeBlock(char* s);
void firstfreeBlock(int size, int start, int end);

DLinkedList *g_heap;
Block freeBlock[100];
int lastfreeBlockIndex = -1;

Block *m_alloc(int size) {
    // write your code..
    if (size <= 0) {
        return NULL;
    }

    Node *freeNode = getfreeBlock(freeBlock, size);
    if (freeNode == NULL) {
        return NULL;
    }
    Block freeNodeBlock;
    freeNodeBlock.size = freeNode->item.size;
    freeNodeBlock.start = freeNode->item.start;
    freeNodeBlock.end = freeNode->item.end;
    Block newBlock;

    newBlock.size = size;
    newBlock.start = freeNodeBlock.start;
    newBlock.end = newBlock.start + size - 1;

    Node *newNode = (Node *) malloc(sizeof(Node));
    newNode->item = newBlock;

    if (g_heap->size == 1) {
        addFirst(g_heap, newNode);
    } else {
        addBefore(freeNode, newNode);
        g_heap->size++;
    }

    delfreeBlock(freeNodeBlock);
    if (newBlock.end == freeNodeBlock.end) {
        removeNode(freeNode);
        return &newBlock;
    } else {
        freeNodeBlock.start = newBlock.end + 1;
        freeNodeBlock.size -= newBlock.size;
        freeNode->item = freeNodeBlock;
        addfreeBlock(freeNodeBlock);
        return &newBlock;
    }

    return NULL;
}

void m_free(Block block) {
    // write your code..
    Node *freeNode = g_heap->header->next;

    while(freeNode->item.start != block.start) {
        freeNode = freeNode->next;
    }

    addfreeBlock(freeNode->item);
    rearrangefreeBlock();
}

Node* getfreeBlock(Block freeBlock[], int size) {
    Node* freeNode;

    for (int i = 0; i <= lastfreeBlockIndex; i++) {
        if (freeBlock[i].size >= size) {
            freeNode = findNode(g_heap, freeBlock[i]);
            
            return freeNode;
        }
    }

    return NULL;
}

void addfreeBlock(Block block) {
    int i, blockIndex = 0;

    for (i = 0; i <= lastfreeBlockIndex; i++) {
        if (block.start < freeBlock[i].start) {
            blockIndex = i;
            break;
        }
    }

    for (i = lastfreeBlockIndex + 1; i > blockIndex; i--) {
        freeBlock[i] = freeBlock[i - 1];
    }
    freeBlock[blockIndex] = block;
    lastfreeBlockIndex++;
}

void delfreeBlock(Block block) {
    int i, blockIndex = 0;

    for (i = 0; i <= lastfreeBlockIndex; i++) {
        if (block.start == freeBlock[i].start) {
            blockIndex = i;
            break;
        }
    }
    for (i = blockIndex; i < lastfreeBlockIndex; i++) {
        freeBlock[i] = freeBlock[i+1];
    }
    lastfreeBlockIndex--;
}

void rearrangefreeBlock() {
    Node* foreNode;
    Node* backNode;
    int check = lastfreeBlockIndex;
    int diff = 0;

    while (check) {
        check = lastfreeBlockIndex;
        for (int i = 0; i < lastfreeBlockIndex; i++) {
            diff = freeBlock[i + 1].start - freeBlock[i].end;
            if (diff == 1) {
                foreNode = findNode(g_heap, freeBlock[i]);
                backNode = findNode(g_heap, freeBlock[i + 1]);
                freeBlock[i].size += freeBlock[i + 1].size;
                freeBlock[i].end = freeBlock[i + 1].end;
                foreNode->item = freeBlock[i];
                delfreeBlock(freeBlock[i + 1]);
                removeNode(backNode);
                g_heap->size--;
            } else {
                check--;
            }
        }
    }
}

/*
 * Do not modify this function !
 */
void getHeap(DLinkedList *heap) {
    Node *tmpNode = heap->header->next;
    printf("size: %d\n", heap->size);
    printf("Header] <-> ");

    while(tmpNode != heap->trailer) {
        printf("[%d<-(%d)->%d] <-> ",
        tmpNode->item.start, tmpNode->item.size, tmpNode->item.end);
        tmpNode = tmpNode->next;
    }

    printf("[Trailer\n");
}

#endif
