/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

import org.apache.log4j.Logger;

/**
 *
 * @author miguelangel
 */
public class ErrorPoolConfigException extends Exception {
     private static Logger log = Logger.getLogger(ErrorPoolConfigException.class);

    
    public ErrorPoolConfigException(String mensaje){
        super(mensaje);
        log.error(mensaje);
    }
}
