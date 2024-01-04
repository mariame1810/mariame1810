OBJS	= main.o
SOURCE	= main.c
HEADER	= basicFunctions.h helpers.h rsa.h crypto.h key.h signature.h protected.h block.h cellKey.h cellProtected.h cellTree.h hash.h vote.h
OUT	= cryptography
CC	 = gcc
FLAGS	 = -g -c -Wall
LFLAGS	 = 

all: $(OBJS)
	$(CC) -g $(OBJS) -o $(OUT) $(LFLAGS)

main.o: main.c
	$(CC) $(FLAGS) main.c 


clean:
	rm -f $(OBJS) $(OUT)
