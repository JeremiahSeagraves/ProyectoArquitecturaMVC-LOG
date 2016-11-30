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
public class FalloInstanciaDeClase extends Exception{
    
    private final String mensaje = "Ocurrio un error al instanciar la clase controlador proporcionada";
    
    public String getMensaje(){
        return mensaje;
    }
}
