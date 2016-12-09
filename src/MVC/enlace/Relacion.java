/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.enlace;

/**
 *
 * @author Milka
 */
public class Relacion {

    private String operacion;
    private String controlador;
    private String operacionCtrl;
    
    public Relacion(String operacion, String controlador, String operacionCtrl){
        this.controlador = controlador;
        this.operacion = operacion;
        this.operacionCtrl = operacionCtrl;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getControlador() {
        return controlador;
    }

    public String getOperacionCtrl() {
        return operacionCtrl;
    }
}
