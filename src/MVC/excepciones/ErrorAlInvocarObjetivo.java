/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.excepciones;

import org.apache.log4j.Logger;

/**
 *
 * @author Milka
 */
public class ErrorAlInvocarObjetivo extends Exception{
    
    private final String mensaje = "Hubo un error al invocar la clase o método";
    private static Logger log = Logger.getLogger(ErrorAlInvocarObjetivo.class);
    
    public String getMensaje(){
        log.error(mensaje);
        return mensaje;
    }
}
