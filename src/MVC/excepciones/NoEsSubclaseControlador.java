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
public class NoEsSubclaseControlador extends Exception{
    private final String mensaje = "La clase proporcionada no es subclase de Controlador";
    private static Logger log = Logger.getLogger(NoEsSubclaseControlador.class);
    
    public String getMensaje(){
        log.error(mensaje);
        return mensaje;
    }
}
