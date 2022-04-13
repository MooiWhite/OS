import java.io.*;
import java.lang.*;
import java.util.concurrent.*; 

class BD{ //Base de Datos compartida por lectores y escritores
 
  private Semaphore mutex = new Semaphore(1); //Para lectores
  private Semaphore bd = new Semaphore(1);	//Para lectores/escritores
  private int rc = 0;	//Cantidad de lectores
  private int info = 0;	//Información de la base

  public void AccesoBase(){
  	try{
  		bd.acquire(); /*Espera si semáforo bd indica ocupado*/ 
  		System.out.println("Acceso exclusivo a la base");
  	}catch(InterruptedException ie){

  	}
  }
  public void LiberaAcceso(){
  	bd.release(); /*Vuelve a db su estado que indica libre*/ 
  	System.out.println("Permiso liberado");
  }
    public void AccesoLlave(){
    try{
    	mutex.acquire();/*Espera si semáforo mutex está ocupado (para lectores)*/ 
  		System.out.println("Cerrojo cerrado");
    }catch(InterruptedException ie){
    }
  	
  }
    public void LiberaLlave(){
  	mutex.release(); /*Libera el candado mutex (para lectores)*/
  	System.out.println("Cerrojo desbloqueado");
  }
  	public void ARegionCritica(){
  	rc++;	/*Aumenta cantidad de lectores actuales*/
  	System.out.println("Incrementó variable 'rc' ");
  }
    public void DRegionCritica(){
  	rc--;	/*Un lector dejó de leer*/ 
  	System.out.println("Incrementó variable 'rc' ");
  }
    public void ActualizaInfo(){
  	info = info +10;/*Se actualiza la información de la base*/ 
  }
    public int getRC(){
  	return rc; /*Retorna catidad de lectores actuales*/ 
  }
     public int getinfo(){
  	return info; /*Retorna el valor de la base*/ 
  }
}
