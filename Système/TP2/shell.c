/*
  Fichier : shell_cp_executetocomplete.c
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

/*---------------------------------------------*/

char	*clear_sc(void)
{
	const char	*clear;

	clear = "\e[1;1H\e[2J";
	write(1, clear, 11);
	return ((char *) NULL);
}

/*---------------------------------------------*/

char *sh_read_line(FILE *f){
  char *line = NULL;
  size_t bufsize = 0; // donc getline realise l'allocation
  getline(&line, &bufsize, f);
  return line;
}

/*---------------------------------------------*/

#define LSH_TOK_BUFSIZE 64
#define LSH_TOK_DELIM " : \t\n"

char ** sh_split_line( char *line){
  int bufsize = LSH_TOK_BUFSIZE, position = 0;
  char **tokens = malloc(bufsize * sizeof(char*));
  char *token;
  
  if (!tokens) {
    fprintf(stderr, "lsh: allocation error\n");
    exit(EXIT_FAILURE);
  }
  
  token = strtok(line, LSH_TOK_DELIM);
  while (token != NULL) {
    tokens[position] = token;
    position++;
    
    if (position >= bufsize) {
      bufsize += LSH_TOK_BUFSIZE;
      tokens = realloc(tokens, bufsize * sizeof(char*));
      if (!tokens) {
        fprintf(stderr, "lsh: allocation error\n");
        exit(EXIT_FAILURE);
      }
    }
    token = strtok(NULL, LSH_TOK_DELIM);
  }
  tokens[position] = NULL;
  return tokens;
}

/*---------------------------------------------*/

int sh_execute(char **args){
  
	pid_t pid;
	int exit_cond;

	pid = fork();
	switch (pid) {
		case -1 : perror("Erreur de création du processus");
			exit(1);
		case 0 : /* Ce code s'exécute chez le fils */
  			execvp(args[0], args);
  			printf("%s: command not found\n", args[0]);
			exit(1);
		default : /* Ce code s'exécute chez le père */
			wait(&exit_cond);	
			return (1);
	}
}

/*---------------------------------------------*/

void sh_free_line(char *line)
{
	free(line);
}

/*---------------------------------------------*/

void sh_free_arg(char **argv)
{
	free(argv);
}

/*---------------------------------------------*/

int check_cmd(char *cmd)
{
	char **forbidden_split;
	char * forbidden;

  	forbidden = strdup(getenv("FORBIDDEN"));// recupere la chaine de caractere comportant les mots interdits

	int i = -1;
	if (!forbidden)
		return (0);
	forbidden_split = sh_split_line(forbidden);
	while (forbidden_split[++i] != NULL)
	{
			if (strstr(cmd, forbidden_split[i])){
			sh_free_arg(forbidden_split);
			free(forbidden);
			return (1);
		}
	}
	free(forbidden);
	sh_free_arg(forbidden_split);
	return (0);
}

/*---------------------------------------------*/

void add_newf(char *forbidden, char ** line_split)
{
	char *tmp = malloc(sizeof(char) * (strlen(forbidden) + strlen(line_split[1]) + 2));
	strcpy(tmp, forbidden);
	strcat(tmp, ":");
	strcat(tmp, line_split[1]);
	setenv("FORBIDDEN", tmp, 1);
	free(tmp);
}

/*---------------------------------------------*/

void remove_fb(char *forbidden, char ** line_split)
{
	int i = 0;
	char *fb = forbidden;
	char *tmp = malloc(sizeof(char) * (strlen(forbidden) + 1));
	if (!strncmp(fb, line_split[1], strlen(line_split[1]))){
		fb += strlen(line_split[1]) + 1;
		if (fb && *fb != '\0')
			strcpy(tmp, fb);
		else
			*tmp = '\0';
	}
	else {
		while (fb && *fb != '\0') {
			if (!strncmp(fb + 1, line_split[1], strlen(line_split[1])))
				fb += strlen(line_split[1]);
			else
				tmp[i++] = *fb;
			fb++;
		}
		tmp[i] = '\0';
	}
	setenv("FORBIDDEN", tmp, 1);
	sh_free_arg(line_split);
	free(tmp);
}

/*---------------------------------------------*/

int builtin(char *line)
{
	if (!line || *line == '\0')
		return(1);
	if (!strncmp(line, "exit", 4)){
		sh_free_line(line);
		clear_sc();
		exit(EXIT_SUCCESS);
	}

  	char *forbidden = getenv("FORBIDDEN");
	char **line_split = sh_split_line(line);

	if (!strncmp(line_split[0], "newf", 4)){
		add_newf(forbidden, line_split);
		return (1);
	}
	if (!strncmp(line_split[0], "rmf", 3)){
		remove_fb(forbidden, line_split);
		return (1);
	}
	sh_free_arg(line_split);
	return (0);
}

/*---------------------------------------------*/

void sh_loop(void){
  	char *prompt = "l3miage shell > ";
  	char *line;
  	char **args;
	char *tmp;

	while(1) {  
    	printf("%s",prompt);
    	fflush(stdout);
    
    	line = sh_read_line(stdin);
		if (!line || *line == '\0')
			continue;
		tmp = strdup(line);
		if (builtin(line))
			continue;
    args = sh_split_line(line);
		if (check_cmd(tmp)){
			sh_free_arg(args);
			free(tmp);
			printf("Travaille au lieu de jouer !\n");
			continue;
		}
		sh_execute(args); 
		free(tmp);	
    	sh_free_line(line);
    	sh_free_arg(args);
  	} 
}

/*---------------------------------------------*/

void handler_sigint(int sig)
{
	(void)sig;
	printf("\nPour quitter, saisir (exit)\n");
	fflush(stdout);
}

/*==============================================*/

int main(int argc, char * argv[]){

	(void)argc;
	(void)argv;
//pour les signaux et gerer le controL C
	struct sigaction action;
    memset(&action,0, sizeof (action));
    action.sa_handler = handler_sigint;
    action.sa_flags = SA_RESTART;
    sigaction(SIGINT, &action,NULL);
  // Init : Load config files, if any

	// clear term
	clear_sc();
	
	setenv("FORBIDDEN", "mp3:mp4:youtube:avi",1);

  // Interp : Run Command loop
  	sh_loop();
  
  // Termin : Perform any shutdown / cleanup
  
  	return EXIT_SUCCESS;
}


