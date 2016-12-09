package MVC.excepciones;

import java.sql.SQLException;

public class ConexionBDException extends SQLException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConexionBDException (String mensaje){
		super(mensaje);
	}
}
