CC = gcc
OBJS = main.c exec_cmd.c history.c cd.c parse_cmd.c parse_redir.c
TARGET = smsh

all : $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -g -o $@ $(OBJS)
	./smsh
