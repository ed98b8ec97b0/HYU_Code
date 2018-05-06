#include <stdio.h>
#include <stdlib.h>

typedef struct tnode{
    struct tnode* left;
    struct tnode* right;
    char val;
} tnode;


typedef struct tree{
    struct tnode* root;
} tree;

tnode* newtnode(char);
tree* newtree();
void inorder(tree*);
void preorder(tree*);
void postorder(tree*);
void insert(tree*, tnode* );

int main(int argc, char const *argv[]){
    tree* t = newtree();
    char a[10] = {'G','D','I','B','F','H','J','A','C','E'};
    int i;
    for(i = 0; i<10;i++){
        insert(t, newtnode(a[i]));
    }
    printf("%s", "inorder: ");
    inorder(t);
    printf("\n%s", "preorder: ");
    preorder(t);
    printf("\n%s", "postorder: ");
    postorder(t);
    printf("\n");
    return 0;
}

tnode* newtnode(char val) {
    tnode* new = (tnode*) malloc(sizeof(tnode));
    new->left = NULL;
    new->right = NULL;
    new->val = val;

    return new;
}

tree* newtree() {
    tree* new = (tree*) malloc(sizeof(tree));
    new->root = NULL;

    return new;
}

void insert(tree* tree, tnode* new) {
    if (tree->root == NULL) {
        tree->root = new;
        return;
    }
    if (tree->root->val > new->val) {
        if (tree->root->left == NULL) {
            tree->root->left = new;
            return;
        }
        struct tree* subt = newtree();
        subt->root = tree->root->left;
        insert(subt, new);
        free(subt);

    }
    else {
        if (tree->root->right == NULL) {
            tree->root->right = new;
            return;
        }
        struct tree* subt = newtree();
        subt->root = tree->root->right;
        insert(subt, new);
        free(subt);
    }
}

void inorder(tree* tree) {
    if (tree->root->left != NULL) {
        struct tree* subtl = newtree();
        subtl->root = tree->root->left;
        inorder(subtl);
        free(subtl);
    }
    printf("%c ", tree->root->val);
    if (tree->root->right != NULL) {
        struct tree* subtr = newtree();
        subtr->root = tree->root->right;
        inorder(subtr);
        free(subtr);
    }
}

void preorder(tree* tree) {
    printf("%c ", tree->root->val);
    if (tree->root->left != NULL) {
        struct tree* subtl = newtree();
        subtl->root = tree->root->left;
        preorder(subtl);
        free(subtl);
    }
    if (tree->root->right != NULL) {
        struct tree* subtr = newtree();
        subtr->root = tree->root->right;
        preorder(subtr);
        free(subtr);
    }
}

void postorder(tree* tree) {
    if (tree->root->left != NULL) {
        struct tree* subtl = newtree();
        subtl->root = tree->root->left;
        postorder(subtl);
        free(subtl);
    }
    if (tree->root->right != NULL) {
        struct tree* subtr = newtree();
        subtr->root = tree->root->right;
        postorder(subtr);
        free(subtr);
    }
    printf("%c ", tree->root->val);
}