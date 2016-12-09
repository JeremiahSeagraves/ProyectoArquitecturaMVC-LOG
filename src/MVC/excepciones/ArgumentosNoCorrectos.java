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
public class ArgumentosNoCorrectos extends Exception{
    
    private final String mensaje = "Los argumentos insertados en el método no son correctos";
    private static Logger log = Logger.getLogger(ArgumentosNoCorrectos.class);
    
    public String getMensaje(){
        log.error(mensaje);
        return mensaje;
    }
}
