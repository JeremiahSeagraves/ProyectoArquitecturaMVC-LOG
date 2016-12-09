package app;

import archivo.MonitorArchivo;
import MVC.excepciones.ArchivoConfigBDNoEncontradaException;

public class Main {
	
	public static void main(String[] args) {
		MonitorArchivo monitor = null;
		try {
			monitor = new MonitorArchivo("Monitor1","configuracion-bd.xml");
		} catch (ArchivoConfigBDNoEncontradaException e) {
			e.printStackTrace();
		}
		
		monitor.start();
		
		System.out.println("Main ha finalizado");
		
	}
}
