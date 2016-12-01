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
public class ArgumentosNoCorrectos extends Exception{
    
    private final String mensaje = "Los argumentos insertados en el método no son correctos";
    
    public String getMensaje(){
        return mensaje;
    }
}
