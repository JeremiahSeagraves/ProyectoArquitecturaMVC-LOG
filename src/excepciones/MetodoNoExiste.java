/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

import org.apache.log4j.Logger;

/**
 *
 * @author Milka
 */
public class MetodoNoExiste extends Exception{
    
    private final String mensaje = "El método no existe";
    private static Logger log = Logger.getLogger(MetodoNoExiste.class);
    
    public String getMensaje(){
        log.error(mensaje);
        return mensaje;
    }
}
