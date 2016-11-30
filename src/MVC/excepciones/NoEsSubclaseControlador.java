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
public class NoEsSubclaseControlador extends Exception{
    private final String mensaje = "La clase proporcionada no es subclase de Controlador";
    
    public String getMensaje(){
        return mensaje;
    }
}
