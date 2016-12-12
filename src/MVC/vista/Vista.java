
package MVC.vista;

import MVC.enlace.Enlace;
import excepciones.ArgumentosNoCorrectos;
import excepciones.ClaseNoEncontrada;
import excepciones.ErrorAlInvocarObjetivo;
import excepciones.FalloInstanciaDeClase;
import excepciones.MetodoNoExiste;
import excepciones.NoEsSubclaseControlador;
import excepciones.NoSePuedeAccederAlaClase;
import excepciones.ViolacionDeSeguridad;


public abstract class Vista {
    
    private Enlace enlace;
    
    public Vista(){
        enlace = Enlace.getInstanceEnlace();
    }
    
    public void callService(Evento e) throws ClaseNoEncontrada, NoEsSubclaseControlador, FalloInstanciaDeClase, NoSePuedeAccederAlaClase, MetodoNoExiste, ViolacionDeSeguridad, ArgumentosNoCorrectos, ErrorAlInvocarObjetivo{
        enlace.mandarEvento(e);
        
    }
    
    public void setConfiguracion(String xml){
        enlace.setArchivoConfiguracion(xml);
    }
}
