import java.io.*;
import java.lang.*;

class Consumer extends Thread {
   Buffer buff;
   int number;
   
   public Consumer(Buffer c, int number) {
      buff = c;
      this.number = number;
   }
   @Override
   public void run() {
      int value = 0;
         for (int i = 0; i < 10; i++) {
            value = buff.get();
            System.out.println("Consumidor" + this.number+ " got: " + value);
         }
    }
}
