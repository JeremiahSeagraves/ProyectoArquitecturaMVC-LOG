
package MVC.vista;

import enlace.Enlace;
import excepciones.ClaseNoEncontrada;
import excepciones.NoEsSubclaseControlador;


public abstract class Vista {
    
    private Enlace enlace;
    
    public Vista(){
        enlace = Enlace.getInstanceEnlace();
    }
    
    public void callService(Evento evt) throws ClaseNoEncontrada, NoEsSubclaseControlador{
        enlace.mandarEvento(evt);
    }
    
    public void setConfiguracion(String xml){
        enlace.setArchivoConfiguracion(xml);
    }
}
