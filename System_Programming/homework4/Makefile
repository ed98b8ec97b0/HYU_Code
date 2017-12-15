CC = gcc
OBJS = main.c exec_cmd.c exec_cmd_bg.c history.c cd.c parse_cmd.c
TARGET = smsh

all : $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -g -o $@ $(OBJS)
	./smsh
