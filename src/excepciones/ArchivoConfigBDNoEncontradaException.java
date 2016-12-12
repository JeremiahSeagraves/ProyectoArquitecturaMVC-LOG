package excepciones;

import java.io.FileNotFoundException;

public class ArchivoConfigBDNoEncontradaException extends FileNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArchivoConfigBDNoEncontradaException(String mensaje){
		super(mensaje);
	}

}
