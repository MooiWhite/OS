import java.util.*;


class CenaFilosofos {
    
    private String pensar, hambre, comer;
    private String[] state = {"", "", "","" ,""};
    private boolean status;

     /*Método 'Levantar cubieros'*/
    public synchronized boolean pickup(int i) {
      state[i] = "HAMBRE"; //Cuando quiere usar los palillos, es porque tiene hambre. Debe cambiar su estado a HAMBRE
      status = test(i);   //Luego debe preguntarle a sus vecinos

      /*Si después de preguntar, el filósofo no pudo comer
        entonces debe esperar hasta que sus vecinos terminen de 
        comer y le notifiquen (notifyAll) */
      if(state[i] != "COMER"){
          try{
            wait();
          }catch(InterruptedException e) {
          }
      }
      return status; /*Si pudo comer envía true, 
                      pero si no pudo comer y está esperando
                      entonc4es envía false*/
   }
   
   /* Indica que ahora el filósofo ya no tiene más hambre
      deja de comer y se pone a pensar, cambiando su estado
      y haciendo una pregunta a sus vecinos. Se retornará false
      dado que el estado del filósofo que pregunta ya no es de HAMBRE*/
    public synchronized void putdown(int i) {
        state[i] = "PENSAR";
        test((i+4)%5);
        test((i+1)%5);
   }

   public synchronized boolean test(int i){
    /*Si sus vecinos (izquerda y derecha) no están comiendo
      y éste filósofo tiene hambre, entonces cambia su status
      y ahora está comiendo, luego avisa para que quien espera los palillos 
      pueda continuar
      */
    if((state[(i+4)%5] != "COMER") && (state[i]) == "HAMBRE" && (state[(i+1)%5] != "COMER")){
        state[i] = "COMER";
        notifyAll();
        return true;
    }
        return false;
   }

}
