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
public class FalloInstanciaDeClase extends Exception{
    
    private final String mensaje = "Ocurrio un error al instanciar la clase controlador proporcionada";
    private static Logger log = Logger.getLogger(FalloInstanciaDeClase.class);
    
    public String getMensaje(){
        log.error(mensaje);
        return mensaje;
    }
}
