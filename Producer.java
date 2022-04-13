import java.io.*;
import java.lang.*;

class Producer extends Thread{
    Buffer buff;
    int number;

    public Producer(Buffer buff, int number) {
      this.buff = buff;
      this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
        buff.put(i);
        System.out.println("Productor #" + this.number + " put: " + i);
        try {
            sleep((int)(Math.random() * 100));
        } catch (InterruptedException e) { }
        }
    }
}
