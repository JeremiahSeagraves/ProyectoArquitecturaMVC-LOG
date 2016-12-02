package MVC.enlace;

import MVC.controlador.Controlador;
import MVC.excepciones.ArgumentosNoCorrectos;
import MVC.excepciones.ClaseNoEncontrada;
import MVC.excepciones.ErrorAlInvocarObjetivo;
import MVC.excepciones.FalloInstanciaDeClase;
import MVC.excepciones.MetodoNoExiste;
import java.lang.reflect.Method;
import MVC.vista.Evento;
import MVC.excepciones.NoEsSubclaseControlador;
import MVC.excepciones.NoSePuedeAccederAlaClase;
import MVC.excepciones.ViolacionDeSeguridad;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;

public class Enlace {

    private static Enlace instanceEnlace;
    private Parser parser;
    private static Logger log = Logger.getLogger(Enlace.class);

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

    public void mandarEvento(Evento evt) throws ClaseNoEncontrada, NoEsSubclaseControlador, FalloInstanciaDeClase, NoSePuedeAccederAlaClase, MetodoNoExiste, ViolacionDeSeguridad, ArgumentosNoCorrectos, ErrorAlInvocarObjetivo {
        Relacion relacion = parser.obtenerRelacion(evt.getOperacion());
        Class clazz = getSubclaseControlador(relacion.getControlador());
        esClaseDeControlador(clazz);
        Object instanceClazz = getInstanceClass(clazz);
        try {
            enviarAControladorElEvento(clazz).invoke(instanceClazz, evt);
            Method metodo = clazz.getDeclaredMethod(relacion.getOperacionCtrl(), null);
            metodo.invoke(instanceClazz, null);
        } catch (IllegalAccessException ex) {
            log.error("Uncaught exception.", ex);
            throw new NoSePuedeAccederAlaClase();    
        } catch (IllegalArgumentException ex) {
            log.error("Uncaught exception.", ex);
            throw new ArgumentosNoCorrectos();
        } catch (InvocationTargetException ex) {
            log.error("Uncaught exception.", ex);
            throw new ErrorAlInvocarObjetivo();
        } catch (NoSuchMethodException ex) {
            log.error("Uncaught exception.", ex);
            throw new MetodoNoExiste();
        } catch (SecurityException ex) {
            log.error("Uncaught exception.", ex);
            throw new ViolacionDeSeguridad();
        }

    }

    private void esClaseDeControlador(Class clazz) throws NoEsSubclaseControlador {
        if (!clazz.getSuperclass().equals(Controlador.class)) {
            throw new NoEsSubclaseControlador();
        }
    }

    private Class getSubclaseControlador(String claz) throws ClaseNoEncontrada {
        Class clazz;
        try {
            clazz = Class.forName(claz);
            return clazz;
        } catch (ClassNotFoundException ex) {
            throw new ClaseNoEncontrada();
        }
    }

    private Object getInstanceClass(Class clazz) throws FalloInstanciaDeClase, NoSePuedeAccederAlaClase {

        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            throw new FalloInstanciaDeClase();
        } catch (IllegalAccessException ex) {
            throw new NoSePuedeAccederAlaClase();
        }
    }

    private Method enviarAControladorElEvento(Class clazz) throws MetodoNoExiste, ViolacionDeSeguridad {
        Method metodo1;
        try {
            metodo1 = clazz.getSuperclass().getDeclaredMethod("setEvento", Evento.class);
            return metodo1;
        } catch (NoSuchMethodException ex) {
            throw new MetodoNoExiste();
        } catch (SecurityException ex) {
            throw new ViolacionDeSeguridad();
        }
    }
}
