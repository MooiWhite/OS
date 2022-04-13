#include <pthread.h>
#include <semaphore.h>
#include <stdlib.h>
#include <stdio.h>

#define SILLAS 5
#define CLIENTES 10

void *Barbero();
void *Cliente();

sem_t clientes;
sem_t barbero;
pthread_mutex_t mutex;
int num_clientes = 0;
int counter = CLIENTES;

void *Barbero()
{
	while(counter>0 || num_clientes>0){ /* Mientras existan clientes el barbero debe continuar*/
		printf("Durmiendo... zzz\n");
		sem_wait(&clientes);      /*Señalización, si no hay clientes se dueme
									si el semáforo contador marca
									personas en espera, el barbero
									continúa*/
		pthread_mutex_lock(&mutex); /*Se bloquea para que nadie entre 
									  entre a la sección crítica*/
		printf("Clientes %d siendo atendido \n", num_clientes);
		num_clientes=num_clientes-1; /*Invita a sentarse al cliente*/
		sem_post(&barbero);		/*Indica que ya está listo para cortar el pelo. Comienza con el corte*/
		pthread_mutex_unlock(&mutex); /*Termina de modificar las región critica*/
		printf("Terminó de cortar el pelo \n");
	}
}

void *Cliente()
{
	pthread_mutex_lock(&mutex);
	if (num_clientes < SILLAS){
		printf("Se encoló un cliente\n");
		num_clientes=num_clientes+1;		 /* Solo para modificar su turno
								Esta seccion critica es protegida por 
								el mutex*/
		counter = counter -1 ;
		sem_post(&clientes);  /* Semáforo contador*/
							  /*Como no se puede obtener el valor del
							  	semáforo, enonces se apoya de la variable 
							  	num_clientes, así este solo es ocupado para 
							  	indicar al barbero cuando debe dormirse o no
							  	Es decir, que si hay clientes él va a atenderlos
							  	de lo contrario se bloquea (espera o duerme) 
							  	Aquí aumenta la cantidad de clientes*/
		pthread_mutex_unlock(&mutex); /* Libera el acceso a región critica */
		sem_wait(&barbero);   /*Cliente va a poder recibir corte hasta que el
								barbero haga un post sobre este semaforo, 
								indicando que ya está libre para atender otro
								cliente. Este aparta su lugar, aún no recibe corte, 
								pero es el siguiente en ser atendido hasta que el barbero le indique*/ 

	}else{
		printf("Se fue un cliente\n");
		counter = counter -1;
		pthread_mutex_unlock(&mutex);
	}
}

int main(){
	pthread_t cliente[10],peluquero; /*10 clientes, un peluquero*/ 
	pthread_mutex_init(&mutex, NULL); /*Sémaforo mutex*/
	sem_init(&clientes,0,0);/*Sémaforo contador inicializado en 0, porque aún no hay ninún cliente*/
	sem_init(&barbero,0,1);/*Sémaforo barbero, inicializado en 1, porque está disponible en principio*/

	for(int i = 0; i < 10; i++) {
       pthread_create(&cliente[i], NULL, (void *)Cliente, NULL); /* Se crean 10 clientes*/
    }

    pthread_create(&peluquero, NULL, (void *)Barbero,NULL); /* Se crea el único peluquero*/

    for(int i = 0; i < 10; i++) {
        pthread_join(cliente[i], NULL);
    }

    pthread_join(peluquero, NULL);

    pthread_mutex_destroy(&mutex);
    sem_destroy(&clientes);
    sem_destroy(&barbero);	


	return 0;
}