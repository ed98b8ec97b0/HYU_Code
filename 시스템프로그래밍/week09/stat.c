#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <pwd.h>
#include <grp.h>

int main(int argc, char **argv)
{
    char *file_name;
    struct stat file_info;
    struct passwd *my_passwd;
    struct group *my_group;
    int n;

    mode_t file_mode;

    if (argc != 2) {
        printf("Usage: ./file_info [file name]\n");
        exit(-1);
    }

    file_name = argv[1];

    n = stat(file_name, &file_info);
    if (n == -1) {
        perror("Error: ");
        exit(-1);
    }

    file_mode = file_info.st_mode;
    printf("file name: %s\n", file_name);
    printf("========================================\n");
    printf("file type: ");

    if (S_ISREG(file_mode))
        printf("regular file\n");
    else if (S_ISLNK(file_mode))
        printf("symbolic link\n");
    else if (S_ISDIR(file_mode))
        printf("directory\n");
    else if (S_ISCHR(file_mode))
        printf("character device\n");
    else if (S_ISBLK(file_mode))
        printf("block device\n");
    else if (S_ISFIFO(file_mode))
        printf("FIFO\n");
    else if (S_ISSOCK(file_mode))
        printf("socket\n");

    my_passwd = getpwuid(file_info.st_uid);
    my_group = getgrgid(file_info.st_gid);
    printf("OWNER: %s\n", my_passwd->pw_name);
    printf("GROUP: %s\n", my_group->gr_name);
    printf("FILE SIZE IS: %d\n", file_info.st_size);
    printf("LAST READ TIME: %d\n", file_info.st_atime);
    printf("LAST MODIFY TIME: %d\n", file_info.st_mtime);
    printf("NUMBER OF HARD/SYMBOLIC LINK: %d\n", file_info.st_nlink);

    return 0;
}