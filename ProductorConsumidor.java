/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.lang.*;
/**
 *
 * @author Edgar
 */
public class ProductorConsumidor {

    public static void main(String args[]) {
        Buffer buff = new Buffer();
        Producer p1 = new Producer(buff, 1);
        Consumer c1 = new Consumer(buff, 1);
        p1.start(); 
        c1.start();
    }
    
}
