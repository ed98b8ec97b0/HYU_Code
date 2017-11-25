#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <sys/types.h>

int main(int argc, char* argv[])
{
    DIR* dirptr;
    struct dirent* dir;
    int i;

    dirptr = opendir(argv[1]);
    if (dirptr == NULL) {
        perror("No such a directory\n");
        exit(-1);
    }

    // for (i = 0; i < 2; i++) {
    //     do {
    //         dir = readdir(dirptr);
    //         if (dir->d_ino != 0)
    //             printf("%s\n", dir->d_name);
    //     } while (dir);
    //     rewinddir(dirptr);
    //     printf("======================\n");
    // }

    do
    {
        dir = readdir(dirptr);
        if (dir->d_ino != 0)
            printf("%s\n", dir->d_name);
    } while (dir);
    rewinddir(dirptr);
    printf("======================\n");

    closedir(dirptr);
    return 0;
}