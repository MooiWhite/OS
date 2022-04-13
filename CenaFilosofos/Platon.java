import java.util.*;

class Platon extends Thread{
	CenaFilosofos cena;
	int number;
 	
 	public Platon(CenaFilosofos cena, int number){
 		this.cena = cena;
 		this.number = number;
 	}

	@Override
	public void run(){
		System.out.println("Platon en la mesa");
		for (int i = 0; i<20; i++){
			if (cena.pickup(number)){
				System.out.println("Platon comió");		
			}else{
				System.out.println("Platon no pudo comer");		
			}
			cena.putdown(number);
			System.out.println("Platon está pensando");	
		}
	}
}