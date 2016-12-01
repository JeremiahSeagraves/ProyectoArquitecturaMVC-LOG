/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.excepciones;

/**
 *
 * @author Milka
 */
public class MetodoNoExiste extends Exception{
    
    private final String mensaje = "El método no existe";
    
    public String getMensaje(){
        return mensaje;
    }
}
