#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>


int main(int argc, char *argv[])
{
	(void)argc;
	(void)argv;
	
    int cmpt;
    int exit_cond; //exit
    pid_t pid; //pour store le pid

    cmpt = 1;

    while (cmpt <= 5)// creation d'une boucle pour pouvoir creer le pere et fils
    {
        pid = fork();
        switch (pid)
        {
        case -1:
            perror("Erreur de création du processus ");
            exit(1);
        case 0: /* ce code s'éxecute chez le fils*/
            printf("creation du fils %d:{pid=%d} {ppid=%d}", cmpt, getpid(), getppid());
            sleep(5*cmpt);
            exit(0);// qui permet de pas creer de zombie
        default:/*ce code s'execute chez le pere*/
            cmpt++;
            sleep(2);
        }
    }

    printf("Attente de la terminaisons des fils...\n");
    cmpt = 1;
    while (++cmpt <= 4)
    {
        pid = wait(&exit_cond);
    if(WIFEXITED(exit_cond))
		{
        printf("Le fils {pid=%d} s'est terminer correctement : %d\n", pid,WEXITSTATUS(exit_cond));
	}
		else{
    printf("Le fils %d s'est mal termine : %d\n", pid, WTERMSIG (exit_cond));
    }
    exit(0); /*execute par le fils et el pere*/ 
}
}
