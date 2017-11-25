#include <stdio.h>
#include <time.h>
#include <ctype.h>

int main() {
    char var1 = '7';
    char var2 = 'v';
    char sentence[32] = "A RoLlINg StoNe GaThERs No MoSS";

    printf("\nOriginal vlaue1: %c, value2: %c\n", var1, var2);
    printf("Original Sentence: %s\n", sentence);

    printf("\n----- Ex1 -----\n");
    if (isdigit(var1)) {
        printf("variable '%c' is digit\n", var1);
    } else {
        printf("variable '%c' is not digit\n", var1);
    }
    if (isdigit(var2)) {
        printf("variable '%c' is digit\n", var2);
    } else {
        printf("variable '%c' is not digit\n", var2);
    }

    printf("\n----- Ex2 -----\n");
    int upperCount = 0, lowerCount = 0;
    int i = 0;
    while (sentence[i]) {
        if (islower(sentence[i])) {
            lowerCount++;
        } else if (isupper(sentence[i])) {
            upperCount++;
        }
        i++;
    }
    printf("upperCount: %d, lowerCount: %d\n", upperCount, lowerCount);

    printf("\n----- Ex3 -----\n");
    char sentence_upper[32];
    char sentence_lower[32];
    i = 0;
    while (sentence[i]) {
        sentence_upper[i] = toupper(sentence[i]);
        sentence_lower[i] = tolower(sentence[i]);
        i++;
    }
    printf("Uppder sentence: %s\n", sentence_upper);
    printf("Lower sentence: %s\n", sentence_lower);

    printf("\n----- Ex4 -----\n");
    time_t t;
    time(&t);
    printf("time: %s\n", ctime(&t));

    return 0;
}
