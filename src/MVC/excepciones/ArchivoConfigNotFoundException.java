/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.excepciones;

import org.apache.log4j.Logger;

/**
 *
 * @author miguelangel
 */
public class ArchivoConfigNotFoundException extends Exception{
    private static Logger log = Logger.getLogger(ArchivoConfigNotFoundException.class);

    
    public ArchivoConfigNotFoundException(String mensaje){        
        super(mensaje);
        log.error(mensaje);
    }
}
