#include <stdio.h>
#include <stdlib.h>
#include "../headers/node.h"

/* Define  'list' using typedef and struct.
	queue has head, and size */
typedef struct {
	int size;
	struct node* head;
} list;

/* Prototypes */
list* init_list();
void appendTo(list* list, node* newnode);
void delAt(list* list, int n);
void print_list(list* list);

int main(int argc, char const *argv[]) {
	list* linked = init_list();
	int i;
	for (i = 1; i < 6; i++) {
		appendTo(linked, newnode(i));
	}
	print_list(linked);
	delAt(linked, -1);
	delAt(linked, 5);
	print_list(linked);
	delAt(linked, 3);
	print_list(linked);
	return 0;
}

list* init_list() {
	list* temp = (list*) malloc(sizeof(list));
	temp->size = 0;
	temp->head = NULL;

	return temp;
}

void appendTo(list* list, node* newnode) {
	list->size++;
	if (list->head == NULL) {
		list->head = newnode;
		return;
	}
	node* iter = list->head;
	while (iter->next != NULL)
		iter = iter->next;
	iter->next = newnode;
}

void delAt(list* list, int n) {
	if (n < 1 || list->head == NULL || list->size < n) {
		printf("delAt(): %d, out of index.\n", n);
		return;
	}
	node* iter = list->head;
	for (int i = 1; i < n - 1; i++)
		iter = iter->next;
	node* temp = iter->next->next;
	free(iter->next);
	iter->next = temp;
	list->size--;
}

void print_list(list* list) {
	node* temp = list->head;
	for (int i = 0; i < list->size; i++, temp = temp->next)
		printf("[%d] ", temp->val);
	printf("\n");
}