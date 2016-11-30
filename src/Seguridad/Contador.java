/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Angel Basto Gonzalez
 */
public class Contador extends TimerTask {
    private int segundos = 20;
    private Timer timer;

    public Contador(Timer timer) {
        this.timer = timer;
    }
    
    
    @Override
    public void run() {
        segundos--;
        //System.out.println("Bloqueado: " + segundos);
        if(segundos==1){
            timer.cancel();
            AdminLogin.desbloquearSistema();
        }
    }
    
}
