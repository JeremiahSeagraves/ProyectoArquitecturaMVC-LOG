package enlace;

import controlador.Controlador;
import excepciones.ClaseNoEncontrada;
import java.lang.reflect.Method;
import vista.Evento;
import excepciones.NoEsSubclaseControlador;
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

    public void mandarEvento(Evento evt) throws ClaseNoEncontrada, NoEsSubclaseControlador{
        Class clazz = getSubclaseControlador(evt.getClase());
        esClaseDeControlador(clazz);
        Object instanceClazz = clazz.newInstance();
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
    
    private Object getInstanceClass(Class clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(Enlace.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Enlace.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
