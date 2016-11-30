
package MVC.vista;

import MVC.enlace.Enlace;
import MVC.excepciones.ClaseNoEncontrada;
import MVC.excepciones.FalloInstanciaDeClase;
import MVC.excepciones.NoEsSubclaseControlador;
import MVC.excepciones.NoSePuedeAccederAlaClase;


public abstract class Vista {
    
    private Enlace enlace;
    
    public Vista(){
        enlace = Enlace.getInstanceEnlace();
    }
    
    public void callService(Evento e) throws ClaseNoEncontrada, NoEsSubclaseControlador, FalloInstanciaDeClase, NoSePuedeAccederAlaClase{
        enlace.mandarEvento(e);
    }
    
    public void setConfiguracion(String xml){
        enlace.setArchivoConfiguracion(xml);
    }
}
