#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* reverse(char*);

int main(int argc, char const *argv[]) {
    char str[] = "abcdefghijklmnopqrstuvwxyz";
    printf("%s\n", reverse(str));

    return 0;
}

char* reverse(char* s) {
    int i = 0;
    char* result = (char*) malloc(sizeof(char) * (strlen(s) + 1));

    do {
        *(result + i) = s[strlen(s) - 1 - i];
    } while(s[++i] != '\0');
	result[strlen(s)] = '\0';

    return result;
}
