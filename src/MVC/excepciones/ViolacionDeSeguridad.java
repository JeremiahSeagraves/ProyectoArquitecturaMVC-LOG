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
public class ViolacionDeSeguridad extends Exception{
    
    private final String mensaje = "Ha habido una violación de seguridad";
    
    public String getMensaje(){
        return mensaje;
    }
}
