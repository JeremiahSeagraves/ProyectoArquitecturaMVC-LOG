/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author Milka
 */
public class ClaseNoEncontrada extends Exception{
    private final String mensaje = "La clase proporcionada no existe";
    
    public String getMensaje(){
        return mensaje;
    }
}
