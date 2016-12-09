/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noe
 */
public class MapeoClaseTabla {
    private String nombreClase;
    private String nombreTabla;
    private ArrayList<String> atributosClase;
    private ArrayList<String> camposTabla;
    private int tamanio=0;

    public MapeoClaseTabla() {
        this.atributosClase = new ArrayList<>();
        this.camposTabla = new ArrayList<>();
    }
    
    
    public void agregar(String atributo, String campo){
        atributosClase.add(atributo);
        camposTabla.add(campo);
        tamanio++;
    }
    
    public int tamanio(){
       return tamanio;
    }
    
    public String getCampo(String atributo){
        
        for(int i=0;i<atributosClase.size();i++){
            if((atributosClase.get(i)).equals(atributo)){
                return camposTabla.get(i);
            }
        }
        return null;
    }

  
    public String getNombreClase() {
        return nombreClase;
    }

    
    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

   
    public String getNombreTabla() {
        return nombreTabla;
    }

    
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }
    
   

}
