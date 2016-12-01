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
public class ErrorAlInvocarObjetivo extends Exception{
    
    private final String mensaje = "Hubo un error al invocar la clase o método";
    
    public String getMensaje(){
        return mensaje;
    }
}
