#include <stdio.h>
#include <stdlib.h>
#include "../headers/node.h"

/* Define  'queue' using typedef and struct.
	queue has front, rear, and size */
typedef struct {
	int size;
	struct node* front;
	struct node* rear;
} queue;

/* Prototypes */
queue* init_queue();
void enqueue(queue*, node*);
void dequeue(queue*);
int front(queue*);
void print_queue(queue*);


int main(int argc, char const *argv[]){
	int i;
	queue* q = init_queue();
	enqueue(q, newnode(1));
	printf("front : %d\n", front(q));
	dequeue(q);
	dequeue(q);
	for(i = 2; i<6;i++){
		enqueue(q, newnode(i));
	}
	dequeue(q);
	print_queue(q);
	for(i = 0; i<3;i++) dequeue(q);
	front(q);
}

queue* init_queue() {
	queue* temp = (queue*) malloc(sizeof(queue));

	temp->size = 0;
	temp->front = NULL;
	temp->rear = NULL;

	return temp;
}

void enqueue(queue* queue, node* newnode) {
	queue->size++;
	if (queue->front == NULL) {
		queue->front = newnode;
		queue->rear = newnode;
		return;
	}
	queue->rear->next = newnode;
	queue->rear = newnode;
}

void dequeue(queue* queue) {
	if (queue->front == NULL) {
		printf("dequeue(): queue is empty.\n");
		return;
	}
	node* temp = queue->front;
	queue->front = queue->front->next;
	free(temp);
	queue->size--;
}

int front(queue* queue) {
	if (queue->front == NULL) {
		printf("%s\n", "front(): queue is empty.");
		return 0;
	}
	return queue->front->val;
}


void print_queue(queue* queue) {
	node* iter = queue->front;

	printf("%s", "Q: ");
	for (int i = 1; i <= queue->size; i++, iter = iter->next) {
		printf("[%d] ", iter->val);
	}
	printf("\n");
}
