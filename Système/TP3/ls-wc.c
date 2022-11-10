
#include <stdio.h>
 #include <stdlib.h>
 #include <unistd.h>
 #include <sys/types.h>
 #include <sys/wait.h>
 #include <sys/socket.h>
 #include <netinet/in.h>
 #include <string.h>

#define BUFFER_SIZE 25
#define READ_END 0 
#define WRITE_END 1
#define BUFFCHAR 256
int main(int argc, char argv[]) {
    int status;
    char cmd[] = "ls";
    char options[] = "-l";
    int * fd;
    char message[BUFFCHAR];

    
    int fd1[2]; 
    if (pipe(fd1) == -1) {
        fprintf(stderr,"Pipe failed");
        return 1;
        }
    if (fork()==0){ 
        close(fd1[0]);
        dup2(fd1[1],STDOUT_FILENO);
        execlp(cmd, cmd, options, NULL, NULL);
        exit(2);
    }else{
        close(fd1[1]);
    }
    sleep(1);
    read(fd1[0], message, 256);
    fprintf(stdout, "%s\n", message);
    wait(&status);
    return EXIT_SUCCESS;
}