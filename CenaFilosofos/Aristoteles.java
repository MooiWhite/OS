import java.util.*;

class Aristoteles extends Thread{
	CenaFilosofos cena;
	int number;
 	
 	public Aristoteles(CenaFilosofos cena, int number){
 		this.cena = cena;
 		this.number = number;
 	}

	@Override
	public void run(){
		System.out.println("Aristoteles en la mesa");
		for (int i = 0; i<20; i++){
			if (cena.pickup(number)){
				System.out.println("Aristoteles comió");		
			}else{
				System.out.println("Aristoteles no pudo comer");		
			}
			cena.putdown(number);
			System.out.println("Aristoteles está pensando");	
		}
	}
}