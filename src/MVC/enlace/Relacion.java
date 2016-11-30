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
    public String controlador;
    public String[] operacionesVista;
    public String[] operacionesControlador;
    
    public Relacion(String clazzControlador, int numeroDeRelaciones){
        this.controlador = clazzControlador;
        this.operacionesControlador = new String[numeroDeRelaciones];
        this.operacionesVista = new String[numeroDeRelaciones];
    }
    
    public void setRelacion(String operacionVista, String operacionControlador, int n){
        this.operacionesControlador[n] = operacionControlador;
        this.operacionesVista[n] = operacionVista;
    }
    
    public String getOperacionControlador(String operacionVista){
        for (int i = 0; i < operacionesVista.length; i++) {
            if (this.operacionesVista[i].equals(operacionVista)) {
                return operacionesControlador[i];
            }
        }
        return null;
    }
    
}
