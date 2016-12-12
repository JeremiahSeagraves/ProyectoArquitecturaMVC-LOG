/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import excepciones.ConnectionsInUseException;

import excepciones.ArchivoConfigNotFoundException;
import excepciones.ErrorPoolConfigException;

/**
 *
 * @author miguelangel
 */
public class MonitorArchivoConfiguracion extends Thread{

    private String ruta = "";
    private Calendar calendarioUltimaModificacion = null;
    private Calendar calendarioActual = null;
    private File archivoConfiguracion = null;
    private AdminPool adminPool;
    private ParserXML parser;

    private static final String ARCHIVO_NO_ENCONTRADO = "El archivo de configuracion del Pool especificado no ha sido encontrado.";

    public MonitorArchivoConfiguracion(String nombre, String ruta) {
        calendarioUltimaModificacion = new GregorianCalendar();
        this.ruta = ruta;
        adminPool = AdminPool.getInstance();
        parser = new ParserXML(this.ruta);
        inicializaArchivo();
    }

    @Override
    public void run() {
        actualizarFechas();
        System.out.println("Se ha comenzado a monitorear al archivo: \"" + getRuta() + "\"");
        while (true) {
            inicializaArchivo();
            try {
                existeArchivo();
                
                if(cambioArchivo()){
                    actualizarConfiguracion();   
                    adminPool.changePoolConnections();
                }                
            } catch (ArchivoConfigNotFoundException ex) {
                ex.printStackTrace();
            } catch (ConnectionsInUseException ex) {
                ex.printStackTrace();
            }
            
            actualizarFechas();
        }
    }

    private void actualizarFechas() {
        calendarioActual = new GregorianCalendar();
        long tiempoUltimaModificacion = archivoConfiguracion.lastModified();
        Date fechaUltimaModificacion = new Date(tiempoUltimaModificacion);
        calendarioUltimaModificacion.setTime(fechaUltimaModificacion);
    }

    private void inicializaArchivo() {
        this.archivoConfiguracion = new File(getRuta());
        try {            
            this.parser.cargarXml();
        } catch (ErrorPoolConfigException ex) {
            ex.printStackTrace();
        }
    }

    private void existeArchivo() throws ArchivoConfigNotFoundException {
        if (!archivoConfiguracion.exists()) {
            throw new ArchivoConfigNotFoundException(ARCHIVO_NO_ENCONTRADO);
        }
    }

    private void actualizarConfiguracion() {
        try {
            parser.cargarXml();
        } catch (ErrorPoolConfigException ex) {
            ex.printStackTrace();
        }
    }

    private boolean cambioArchivo() {
        if (calendarioActual.compareTo(calendarioUltimaModificacion) == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public AdminPool getAdminPool(){
        return adminPool;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }    
}
