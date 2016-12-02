package app;

import archivo.MonitorArchivo;
import excepciones.ArchivoConfigBDNoEncontradaException;

public class Main {
	
	public static void main(String[] args) {
		MonitorArchivo monitor = null;
		try {
			monitor = new MonitorArchivo("Monitor1");
		} catch (ArchivoConfigBDNoEncontradaException e) {
			e.printStackTrace();
		}
		monitor.setRuta("configuracion-bd.xml");
		
		monitor.start();
		
		System.out.println("Main ha finalizado");
		
	}
}
