//
//  DLinkedList.h
//  HW2_MemoryManagement
//
//  Copyright © 2017년 2학기 System Programming. All rights reserved.
//
//  이름 : 장호연
//  학번 : 2015038568
//  학과 : 컴퓨터공학과
//

#ifndef _DLinkedList_H_
#define _DLinkedList_H_

#include <stdio.h>
#include <stdbool.h>
#include "Block.h"
#include "Node.h"

typedef struct DLinkedList {
    int size;
    struct Node *header;
    struct Node *trailer;
} DLinkedList;

void setHeaderInfo(DLinkedList *dl, Block info);
void setTrailerInfo(DLinkedList *dl, Block info);
bool isEmpty(DLinkedList *dl);
Node *getFirst(DLinkedList *dl);
Node *getLast(DLinkedList *dl);
void addFirst(DLinkedList *dl, Node *newNode);
void addLast(DLinkedList *dl, Node *newNode);
Block *removeFirst(DLinkedList *dl);
Block *removeLast(DLinkedList *dl);
void addAfter(Node *node, Node *newNode);
void addBefore(Node *node, Node *newNode);
Block *removeNode(Node *node);

/*
 * set header information of dl
 */
void setHeaderInfo(DLinkedList *dl, Block info) {
    dl->header->item = info;
}

/*
 * set trailer information of dl
 */
void setTrailerInfo(DLinkedList *dl, Block info) {
    dl->trailer->item = info;
}

/*
 * if empty, return true;
 * if not empty, return false;
 * bool is from <stdbool.h>
 */
bool isEmpty(DLinkedList *dl) {
    // write your code..
    if (dl->header->next == dl->trailer) {
        return true;
    }
    return false;
}

/*
 * return dl's size
 */
int getSize(DLinkedList *dl) {
    return dl->size;
}

/*
 * return dl's first Node
 */
Node *getFirst(DLinkedList *dl) {
    // write your code..
    if (dl->header->next != dl->trailer) {
        return dl->header->next;
    }
    return NULL;
}

/*
 * return dl's last Node
 */
Node *getLast(DLinkedList *dl) {
    // write your code..
    if (dl->trailer->prev != dl->header) {
        return dl->trailer->prev;
    }
    return NULL;
}

/*
 * add newNode to dl's first position
 */
void addFirst(DLinkedList *dl, Node *newNode) {
    // write your code..
    newNode->next = dl->header->next;
    dl->header->next->prev = newNode;
    dl->header->next = newNode;
    dl->size++;
}

/*
 * add newNode to dl's last position
 */
void addLast(DLinkedList *dl, Node *newNode) {
    // write your code..
    newNode->prev = dl->trailer->prev;
    dl->trailer->prev->next = newNode;
    dl->trailer->prev = newNode;
    dl->size++;
}

/*
 * remove dl's first node
 */
Block *removeFirst(DLinkedList *dl) {
    // write your code..
    Node *tmpNode = dl->header->next;
    dl->header->next = tmpNode->next;
    tmpNode->next->prev = dl->header;
    tmpNode->prev = NULL;
    tmpNode->next = NULL;
    free(tmpNode);
    dl->size--;
    return NULL;
}

/*
 * remove dl's last node
 */
Block *removeLast(DLinkedList *dl) {
    // write your code..
    Node *tmpNode = dl->trailer->prev;
    dl->trailer->prev = tmpNode->prev;
    tmpNode->prev->next = dl->trailer;
    tmpNode->prev = NULL;
    tmpNode->next = NULL;
    free(tmpNode);
    dl->size--;
    return NULL;
}

/*
 * add newNode after the node
 */
void addAfter(Node *node, Node *newNode) {
    // write your code..
    Node* prevNode = node->prev;
    Node* nextNode = node->next;

    newNode->prev = node;
    newNode->next = nextNode;
    nextNode->prev = newNode;
    node->next = newNode;
}

/*
 * add newNode before the node
 */
void addBefore(Node *node, Node *newNode) {
    // write your code..
    Node* prevNode = node->prev;
    Node* nextNode = node->next;

    newNode->prev = prevNode;
    newNode->next = node;
    prevNode->next = newNode;
    node->prev = newNode;
}

/*
 * remove the node
 */
Block *removeNode(Node *node) {
    // write your code..
    node->prev->next = node->next;
    node->next->prev = node->prev;
    node->prev = NULL;
    node->next = NULL;
    free(node);
    return NULL;
}

Node* findNode(DLinkedList *dl, Block block) {
    Node* temp = dl->header->next;
    while (temp->item.start != block.start) {
        temp = temp->next;
    }
    return temp;
}

#endif
