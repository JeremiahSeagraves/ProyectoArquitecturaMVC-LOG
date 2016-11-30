package MVC.enlace;

import MVC.controlador.Controlador;
import MVC.excepciones.ClaseNoEncontrada;
import MVC.excepciones.FalloInstanciaDeClase;
import java.lang.reflect.Method;
import MVC.vista.Evento;
import MVC.excepciones.NoEsSubclaseControlador;
import MVC.excepciones.NoSePuedeAccederAlaClase;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enlace {

    private static Enlace instanceEnlace;
    private Parser parser;

    private Enlace() {
        this.parser = new Parser();
    }

    public static Enlace getInstanceEnlace() {
        if (instanceEnlace == null) {
            return instanceEnlace = new Enlace();
        } else {
            return instanceEnlace;
        }
    }

    public void setArchivoConfiguracion(String archivo) {
        this.parser.setArchivConfiguracion(archivo);
        this.parser.obtenerEnlaces();
    }

    public void mandarEvento(Evento evt) throws ClaseNoEncontrada, NoEsSubclaseControlador, FalloInstanciaDeClase, NoSePuedeAccederAlaClase{
        Class clazz = getSubclaseControlador(evt.getClase());
        esClaseDeControlador(clazz);
        Object instanceClazz = getInstanceClass(clazz);
        Method metodo1 = clazz.getSuperclass().getDeclaredMethod("setEvento", Evento.class);
        metodo1.invoke(instanceClazz, evt);
        Method metodo = clazz.getDeclaredMethod(parser.obtenerOperacionControlador(evt.getClase(), evt.getOperacion()), null);
        metodo.invoke(instanceClazz, null);

    }

    
    
    private void esClaseDeControlador(Class clazz) throws NoEsSubclaseControlador {
        if (!clazz.getSuperclass().equals(Controlador.class)) {
            throw new NoEsSubclaseControlador();
        }
    }
    
    private Class getSubclaseControlador(String claz) throws ClaseNoEncontrada{
        Class clazz;
        try {
            clazz = Class.forName(claz);
            return clazz;
        } catch (ClassNotFoundException ex) {
            throw new ClaseNoEncontrada();
        }
    }
    
    private Object getInstanceClass(Class clazz) throws FalloInstanciaDeClase, NoSePuedeAccederAlaClase{
        
        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            throw new FalloInstanciaDeClase();
        } catch (IllegalAccessException ex) {
            throw new NoSePuedeAccederAlaClase();
        }
        return null;
    }

}
