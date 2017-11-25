#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>

DIR* set_dirptr(char* path)
{
    DIR *dirptr;
    
    dirptr = opendir(path);
    if (dirptr == NULL) {
        perror("No such a directroy\n");
        exit(-1);
    }

    return dirptr;
}

struct stat my_stat(char *name)
{
    int n;
    struct stat file_info;

    n = stat(name, &file_info);
    if (n == -1)
    {
        perror("stat");
        printf("%s\n", name);
        exit(-1);
    }

    return file_info;
}

static int count = 0;

void myftw(DIR *dirptr, char *path)
{
    struct dirent *dir;
    struct stat file_info;
    mode_t file_mode;
    char cur_dir[255], c, tmp[255], *token = NULL;
    DIR *indirptr;

    while (dir = readdir(dirptr))
    {
        if (dir->d_ino != 0)
        {   
            if (count == 0)
            {
                strcpy(tmp, path);
                token = strtok(path, "/");
                c = (char) path[0];
                if (c == '/')
                {
                    strcpy(cur_dir, "/");
                    strcat(cur_dir, token);
                }
                else
                    strcpy(cur_dir, token);

                token = strtok(NULL, "/");
                while (token != NULL)
                {   
                    strcat(cur_dir, "/");
                    strcat(cur_dir, token);
                    token = strtok(NULL, "/");
                }
                strcpy(path, tmp);
            } else {
                strcpy(cur_dir, path);
            }

            if (count++ == 0)
            {
                file_info = my_stat(dir->d_name);
                file_mode = file_info.st_mode;
                printf("%-50s\t*0%o\n", dir->d_name, file_mode & 0777);
                continue;
            }
            if (strcmp(dir->d_name, ".") == 0)
                continue;
            if (strcmp(dir->d_name, "..") == 0)
                continue;
            
            strcat(cur_dir, "/");
            strcat(cur_dir, dir->d_name);
            file_info = my_stat(cur_dir);
            file_mode = file_info.st_mode;
            
            if (S_ISREG(file_mode))
                printf("%-50s\t 0%o\n", cur_dir, file_mode & 0777);
            else
                printf("%-50s\t*0%o\n", cur_dir, file_mode & 0777);

            if (S_ISDIR(file_mode)) {
                indirptr = set_dirptr(cur_dir);
                myftw(indirptr, cur_dir);
                close(indirptr);
            }
        }
    }
}

int main(int argc, char *argv[])
{
    DIR *dirptr;

    dirptr = set_dirptr(argv[1]);
    myftw(dirptr, argv[1]);
    closedir(dirptr);

    return 0;
}
