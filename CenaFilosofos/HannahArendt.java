import java.util.*;

class HannaArendt extends Thread{
	CenaFilosofos cena;
	int number;
 	
 	public HannaArendt(CenaFilosofos cena, int number){
 		this.cena = cena;
 		this.number = number;
 	}

	@Override
	public void run(){
		System.out.println("Hannah Arendt en la mesa");
		for (int i = 0; i<20; i++){
			if (cena.pickup(number)){
				System.out.println("Hannah comió");		
			}else{
				System.out.println("Hannah no pudo comer");		
			}	
			cena.putdown(number);
			System.out.println("Hannah está pensando");	
		}
	}
}