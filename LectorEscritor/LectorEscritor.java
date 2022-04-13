import java.util.concurrent.*; 

class Escritor extends Thread {
   BD baseDatos ; /*Un escritor tiene acceso a una base*/
   
   public Escritor(BD baseDatos) { /*Constructor*/
      this.baseDatos=baseDatos;
   }
   @Override
   public void run() {
         for(int i=0; i<10; i++){ /*Simulará 10 escritores queriendo actualizar*/
            try {
               sleep(1000);  //Se dormirán en cada iteración para mejor vista
            }catch(InterruptedException ie){}
            System.out.println("Inventando datos...");
            baseDatos.AccesoBase(); /*Solicitad acceso a la base*/
            System.out.println("Actualizando datos de la base...");
            baseDatos.ActualizaInfo();/*Actualiza la información*/
            baseDatos.LiberaAcceso();/*Libera acceso a la base*/
         } 
}
}

class Lector extends Thread { 
   BD baseDatos; /*Un lector tiene acceso a una base*/
   public Lector(BD baseDatos) { /*Constructor*/
      this.baseDatos = baseDatos;
   }
   @Override
   public void run() {
         for(int i=0; i<20; i++){/*Simulará 20 lectores queriendo leer*/
            try {
               sleep(1000);   //Se dormirán en cada iteración para mejor vista
            }catch(InterruptedException ie){}
            baseDatos.AccesoLlave();/*Solicitad acceso a la llave de lectores para modificar la cantidad de lectores*/
            baseDatos.ARegionCritica();/*Aumenta la cantidad de lectores*/
            System.out.println("RC = "+baseDatos.getRC());
            if (baseDatos.getRC() == 1){
               baseDatos.AccesoBase();/*Si es el primero pide permiso a la base*/
            }
            baseDatos.LiberaLlave();/*Libera la llave de lectores que modificaba la cantidad de lectores */
            System.out.println("Leyendo base ...");
            System.out.println(baseDatos.getinfo());
            baseDatos.AccesoLlave();/*Vuelve a pedir la llave para modificar lectores*/
            baseDatos.DRegionCritica();/*Ya hay un lector menos que lee la base*/
            System.out.println("RC = "+baseDatos.getRC());
            if(baseDatos.getRC() == 0){
               baseDatos.LiberaAcceso();/*Como ha terminado libera acceso a base por si hay un escritor esperando*/
            }
            baseDatos.LiberaLlave();/*Ahora si cede la llave lectores por sui otro lector quiere leer*/
            System.out.println("Usando datos leídos...");
         }
    }
}

public class LectorEscritor //Lectores Escritores Test
{ 
   public static void main(String args[])
   { 
      BD baseDatos = new BD(); 
      Lector lector = new Lector(baseDatos);
      Escritor escritor = new Escritor(baseDatos);
      escritor.start();
      lector.start();
  
   } 
} 

