import java.util.*;

class Descartes extends Thread{
	CenaFilosofos cena;
	int number;
 	
 	public Descartes(CenaFilosofos cena, int number){
 		this.cena = cena;
 		this.number = number;
 	}

	@Override
	public void run(){
		System.out.println("Descartes en la mesa");
		/* El proceso se repite 20 veces, por lo que este filósofo 
		   solicitará acceso a los cubiertos, preguntará (implicítamente)
		   y pensará 20 veces*/
		/* Esto mismo lo harán los demás filósofos*/
		for (int i = 0; i<20; i++){
			if (cena.pickup(number)){ /*Recordando, devuelve uno cuando el filósofo 
										ya comío, no importa si esperó o no, pero si devuelve 0	
										significa que ya comió*/
									  /*Se le envía como parámetro el id o identificador del 
									  	filosófo "number" */
				System.out.println("Descartes comió");		
			}else{
										/*Recordando que si envía false es porque falló su
										  intento de comer */
				System.out.println("Descartes no pudo comer");		
			}	
			cena.putdown(number);	/* Cambia su estado a pensar*/
			System.out.println("Descartes está pensando");	
		}
		
	}
}