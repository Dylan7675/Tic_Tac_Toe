/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

/**
 *
 * @author dylan laptop
 */
public class MyThread implements Runnable{
    public void start()
    {
        try{
        Thread.sleep(1000);
        }catch(InterruptedException ex){
        Thread.currentThread().interrupt();}
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
