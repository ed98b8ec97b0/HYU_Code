#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* concat(char*, char*);

int main(int argc, char const *argv[]) {
    char* a = "Hello,";
    char* b = "World!";
    char* string = concat(a, b);

    printf("%s\n", string);
}

char* concat(char* a, char* b) {
    char* result;
    
    result = (char*) malloc(sizeof(char) * (strlen(a) + strlen(b) + 2));
    strcpy(result, a);
    result[strlen(a)] = ' ';
    strcpy(&result[strlen(a) + 1], b);
    result[strlen(a) + strlen(b) + 2] = '\0';

    return result;
}
