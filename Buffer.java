import java.io.*;
import java.lang.*;

class Buffer {
    
    private int contents;
    private boolean available = false;
    public synchronized int get() {
      while (available == false) {
        System.out.println("no pude tomar");
          try {
            wait();
         }
         catch (InterruptedException e) {
         }
      }
        System.out.println("Pude tomar");
      available = false;
      notifyAll();
      return contents;
   }
   
    public synchronized void put(int value) {
        while (available == true) {
        try {
            wait();
        }
        catch (InterruptedException e) { 
        } 
      }
      contents = value;
      available = true;
      notifyAll();
   }
}
