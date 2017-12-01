#include "header.h"
#include "lru.h"

queue *cache;

void error(char *msg)
{
    perror(msg);
    exit(1);
}

void my_life_for_pipe()
{
    ;
}

int main(int argc, char* argv[])
{
    int proxy_sock, cli_sock, serv_sock;
    int port_no;
    int n, m, flag;
    char *buffer, *url, *path;
    buffer = (char *)malloc(sizeof(char) * BUFF_SIZE);
    url = (char *)malloc(sizeof(char) * URL_SIZE);
    path = (char *)malloc(sizeof(char) * PATH_SIZE);
    char token1[URL_SIZE], token2[URL_SIZE], token3[10];
    char* temp = NULL;
    struct sockaddr_in proxy_addr, serv_addr, cli_addr;
    struct hostent *server;
    socklen_t clilen;
    cache = (queue *)malloc(sizeof(queue));
    reset_queue(cache);

    if (argc < 2)
    {
        fprintf(stderr, "ERROR argument");
        exit(1);
    }
    signal(SIGPIPE, my_life_for_pipe);

    printf("Proxy server\n");
    printf("Computer Network Project2\n");
    printf("Hanyang University ERICA Campus\n");
    printf("2015038568 장호연\n\n");

    // proxy server socket open
    proxy_sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (proxy_sock < 0)
    {
        error("ERROR proxy socket");
    }
    

    // reset proxy_addr
    memset((char *) &proxy_addr, 0, sizeof(proxy_addr));
    port_no = atoi(argv[1]);
    proxy_addr.sin_family = AF_INET;
    proxy_addr.sin_addr.s_addr = INADDR_ANY;
    proxy_addr.sin_port = htons(port_no);

    int opt = 1;
    setsockopt(proxy_sock, SOL_SOCKET, SO_REUSEADDR, (const char *)&opt, sizeof(int));

    // binding proxy socket
    n = bind(proxy_sock, (struct sockaddr *) & proxy_addr, sizeof(proxy_addr));
    if (n < 0)
    {
        error("ERROR bind proxy socket");
    }

    // proxy socket into listening mode 
    n = listen(proxy_sock, BACKLOG);
    if (n < 0)
    {
        error("ERROR listen proxy socket");
    }

    while(1)
    {
        print_queue();
        clilen = sizeof(cli_addr);
        memset(token1, 0, URL_SIZE);
        memset(token2, 0, URL_SIZE);
        memset(token3, 0, 10);
        memset(buffer, 0, BUFF_SIZE);
        memset(url, 0, URL_SIZE);
        memset(path, 0, PATH_SIZE);
        // accepting client socket
        cli_sock = accept(proxy_sock, (struct sockaddr *) &proxy_addr, &clilen);
        if (cli_sock < 0)
        {
            error("ERROR accept client socket");
        }

        // reset buffer
        memset(buffer, 0, BUFF_SIZE);

        // read client socket
        n = read(cli_sock, buffer, BUFF_SIZE-1);
        if (n < 0)
        {
            error("ERROR read client socket");
        }

        // split first headline
        n = 0;
        sscanf(buffer, "%s %s %s", token1, token2, token3);

        if (((strncmp(token1, "GET", 3) == 0)) && ((strncmp(token3, "HTTP/1.1", 8) == 0) || (strncmp(token3, "HTTP/1.0", 8) == 0)) && (strncmp(token2, "http://", 7) == 0))
        {
            strcpy(token1, token2);
            for (int i = 7; i < strlen(token2); i++)
            {
                if (token2[i] == ':')
                {
                    m = 1;
                    continue;
                }
                if ((m == 1) && (isdigit(token2[i]) == 0))
                {
                    m = 0;
                    continue;
                }
                if ((m == 1) && (token2[i] == '/'))
                {
                    n = 1;
                }
            }
            printf("\n");

            // split URL + path and port
            temp = strtok(token2, "//"); // temp = http://
            if (n == 0)
            {
                port_no = 80;
                temp = strtok(NULL, "/"); // temp = URL + path
            }
            else
            {
                temp = strtok(NULL, ":"); // temp = URL + path
            }

            if (n == 1)
            {
                temp = strtok(NULL, "/");
                port_no = atoi(temp);
            }
            sprintf(url, "%s", temp);
            
            // connet to server
            server = gethostbyname(url);
            if (server == NULL)
            {
                n = write(cli_sock, NOT_FOUND, 27);
                if (n < 0)
                {
                    error("ERROR write client socket");
                }
                error("ERROR gethostbyname");
            }
            printf("\n\nConnet to %s\n", url);

            // get path
            strcpy(token2, token1);
            strcat(token1, "^]");
            temp = strtok(token1, "//");
            temp = strtok(NULL, "/");
            if (temp != NULL)
            {
                temp = strtok(NULL, "^]");
            }
            if (temp != NULL)
            {
                sprintf(path, "%s", temp);
            }
            printf("url: %s\npath: %s\n", url, path);
            memset(token1, 0, URL_SIZE);
            printf("token1 = %s\n", token1);
            strcpy(token1, url);
            strcat(token1, "/");
            strcat(token1, path);

            flag = 0;
            flag = check(token1);
            if (flag == 1)
            {
                printf("Cache: Hit\n");
                object* obje = hit(token1);
                packet* pck = obje->data;
                while(pck != NULL)
                {   
                    n = send(cli_sock, pck->buffer, pck->length, 0);
                    if ((n < 0) && (errno != EPIPE))
                    {
                        error("ERROR send in hit");
                    }
                    pck = pck->next;
                }
            }
            else
            {
                printf("Cache: Miss\n");
                memset((char *) &serv_addr, 0, sizeof(serv_addr));
                serv_addr.sin_family = AF_INET;
                memcpy((char *) &serv_addr.sin_addr.s_addr, (char *) server->h_addr, server->h_length);
                serv_addr.sin_port = htons(port_no);

                // proxy server socket open
                serv_sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
                if (serv_sock < 0)
                {
                    error("ERROR proxy socket");
                }

                n = connect(serv_sock, (struct sockaddr *) &serv_addr, sizeof(serv_addr));
                if (n < 0)
                {
                    error("ERROR connect server");
                }

                // write buffer to using receive http response from server.
                memset(buffer, 0, BUFF_SIZE);
                if (temp != NULL)
                {
                    sprintf(buffer, "GET /%s %s\r\nHost: %s\r\nConnection: close\r\n\r\n", path, token3, url);
                }
                else
                {
                    sprintf(buffer, "GET / %s\r\nHost: %s\r\nConnection: close\r\n\r\n", token3, url);
                }

                n = write(serv_sock, buffer, BUFF_SIZE);
                if ((n < 0) && (errno != EPIPE))
                {
                    error("ERROR write proxy");
                }
                
                memset(buffer, 0, BUFF_SIZE);

                // cache first node;
                flag = 0;
                object* obje = (object *)malloc(sizeof(object));
                reset_object(obje);
                packet *temp2;
                // send request and receive response.
                do
                {   
                    n = recv(serv_sock, buffer, BUFF_SIZE, 0);
                    if (n < 0)
                    {
                        error("ERROR read proxy");
                    }
                    if (n != 0)
                    {
                        // consturct object;
                        packet *pck = (packet *)malloc(sizeof(packet));
                        reset_packet(pck);
                        memmove(pck->buffer, buffer, n);
                        obje->length += n;
                        memmove(obje->url, token1, strlen(token1));
                        pck->length = n;
                        if (flag == 0)
                        {
                            obje->data = pck;
                            temp2 = pck;
                        }
                        else
                        {
                            temp2->next = pck;
                            temp2 = temp2->next;
                        }

                        m = send(cli_sock, buffer, n, 0);
                        if ((m < 0) && (errno != EPIPE))
                        {
                            error("ERROR write client socket");
                        }
                    }

                    flag = 1;
                } while (n > 0);

                if (obje->length < OBJE_SIZE)
                {
                    printf("url = %s\n", obje->url);
                    printf("Add cache\n");
                    miss(obje);
                }
            }
        }
        else
        {
            memset(buffer, 0, BUFF_SIZE);
            n = send(cli_sock, BAD_REQUEST, BUFF_SIZE, 0);
            if (n < 0)
            {
                error("ERROR send in hit");
            }
            close(cli_sock);
        }

        memset(buffer, 0, BUFF_SIZE);
        close(serv_sock);
        close(cli_sock);
    }
    close(proxy_sock);

    return 0;
}