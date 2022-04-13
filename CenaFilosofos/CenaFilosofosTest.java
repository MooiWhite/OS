import java.io.*;
import java.lang.*;

public class CenaFilosofosTest{
	public static void main(String args[]){
		CenaFilosofos cena = new CenaFilosofos();
		Aristoteles aristoteles = new Aristoteles(cena,0);
		Descartes descartes = new Descartes (cena,1);
		HannaArendt hanna = new HannaArendt(cena,2);
		Platon platon = new Platon(cena,3);
		Socrates socrates = new Socrates(cena,4);

		aristoteles.start();
		descartes.start();
		hanna.start();
		platon.start();
		socrates.start();
	}
}