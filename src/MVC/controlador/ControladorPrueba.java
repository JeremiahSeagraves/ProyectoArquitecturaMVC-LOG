/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.controlador;

import MVC.controlador.Controlador;


/**
 *
 * @author Milka
 */
public class ControladorPrueba extends Controlador {
 
    public ControladorPrueba(){
        
    }
    
    public void update(){
        System.out.println("update");
        String or = (String)getEvt().getObject().toString();
        System.out.println(or + "Se a guardado exitosamente" );
    }
    
    public void add(){
        System.out.println("add");
    }
    
    public void remove(){
        System.out.println("remove");
    }
    
}
