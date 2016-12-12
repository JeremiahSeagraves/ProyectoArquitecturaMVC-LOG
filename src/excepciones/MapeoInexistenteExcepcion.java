/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

import org.apache.log4j.Logger;

/**
 *
 * @author Noe
 */
public class MapeoInexistenteExcepcion extends Exception{
    private static Logger log = Logger.getLogger(MapeoInexistenteExcepcion.class);


    public MapeoInexistenteExcepcion(String message) {
        super(message);
        log.error(message);
    }
    
}
