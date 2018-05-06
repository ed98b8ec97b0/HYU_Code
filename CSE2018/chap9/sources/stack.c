#include <stdio.h>
#include <stdlib.h>
#include "../headers/node.h"

/* Define 'stack' using typedef and struct.
	stack has top, size */
typedef struct {
	int size;
	struct node* top;
} stack;

/* Prototypes */
stack* init_stack();
void push(stack*, node*);
void pop(stack*);
int top(stack*);
void print_stack(stack*);

int main(int argc, char const *argv[])
{
	int i;
	stack* s = init_stack();
	push(s, newnode(1));
	printf("top : %d\n", top(s));
	pop(s);
	pop(s);
	for (i = 2; i < 6; i++) {
		push(s, newnode(i));
	}
	pop(s);
	print_stack(s);
	for (i = 0; i < 3; i++) pop(s);
	top(s);
	return 0;
}

stack* init_stack() {
	stack* temp = (stack*) malloc(sizeof(stack));

	temp->size = 0;
	temp->top = NULL;

	return temp;
}

void push(stack* stack, node* newnode) {
	stack->size++;
	if (stack->top == NULL) {
		stack->top = newnode;
		return;
	}
	newnode->next = stack->top;
	stack->top = newnode;
}

void pop(stack* stack) {
	if (stack->top == NULL) {
		printf("%s\n", "pop(): stack is empty.");
		return;
	}
	node* temp = stack->top;
	stack->top = stack->top->next;
	free(temp);
	stack->size--;
}

int top(stack* stack) {
	if (stack->top == NULL) {
		printf("%s\n", "top(): stack is empty.");
		return 0;
	}
	return stack->top->val;
}

void print_stack(stack* stack) {
	node* iter = stack->top;

	printf("%s\n", "TOP");
	for (int i = 1; i <= stack->size; i++, iter = iter->next) {
		printf("[%d]\n", iter->val);
	}
}
