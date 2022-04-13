import java.util.*;

class Socrates extends Thread{
	CenaFilosofos cena;
	int number;
 	
 	public Socrates(CenaFilosofos cena, int number){
 		this.cena = cena;
 		this.number = number;
 	}

	@Override
	public void run(){
		for (int i = 0; i<20; i++){
			if (cena.pickup(number)){
				System.out.println("Socrates comió");		
			}else{
				System.out.println("Socrates no pudo comer");		
			}	
			cena.putdown(number);
			System.out.println("Socrates está pensando");	
		}
	}
}