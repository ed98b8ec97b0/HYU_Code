CC = gcc
OBJS = main.c exec_cmd.c history.c cd.c parse_cmd.c
TARGET = smsh

all : $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -o $@ $(OBJS)
	./smsh
