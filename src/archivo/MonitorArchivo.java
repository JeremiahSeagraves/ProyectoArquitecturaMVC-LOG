package archivo;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import excepciones.ConfigBDModificadaException;
import excepciones.ArchivoConfigBDNoEncontradaException;

public class MonitorArchivo extends Thread {
	private String ruta = "";
	private Calendar calendarioUltimaModificacion = null;
	private Calendar calendarioActual = null;
	private File archivoConfiguracion = null;
	private static Logger log = Logger.getLogger(MonitorArchivo.class);

	private static final String ARCHIVO_NO_ENCONTRADO = "El archivo de configuracion BD especificado no ha sido encontrado.";
	private static final String ARCHIVO_MODIFICADO = "El archivo de configuraci�n BD ha sido modificado.";

	public MonitorArchivo(String nombre, String ruta) throws ArchivoConfigBDNoEncontradaException {
		calendarioUltimaModificacion = new GregorianCalendar();
                setRuta(ruta);
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
			if (cambioArchivo()) {
					throw new ConfigBDModificadaException(ARCHIVO_MODIFICADO);
				} 
			}catch ( ArchivoConfigBDNoEncontradaException | ConfigBDModificadaException e) {				
				
					if (e instanceof ConfigBDModificadaException){
						actualizarConfiguracionBD();
						log.warn(ARCHIVO_MODIFICADO);
					}
					else{
						log.error(ARCHIVO_NO_ENCONTRADO);
						break;
					}
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
	
	private void inicializaArchivo(){
		this.archivoConfiguracion = new File(getRuta());
	}

	private void existeArchivo() throws ArchivoConfigBDNoEncontradaException {
		if(!archivoConfiguracion.exists())
			throw new ArchivoConfigBDNoEncontradaException(ARCHIVO_NO_ENCONTRADO);
	}
	
	private void actualizarConfiguracionBD(){
		ParserXML parser = new ParserXML(getRuta());
		parser.cargarXml();
	}

	private boolean cambioArchivo() {
		if (calendarioActual.compareTo(calendarioUltimaModificacion) == 0)
			return true;
		else
			return false;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getRuta() {
		return ruta;
	}
}
