package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import excepciones.ConexionBDException;
import MVC.modelo.DatosConexion;

public class AdminConexiones {
	
	private static Logger log = Logger.getLogger(AdminConexiones.class);
        private static AdminConexiones adminConexiones = null;
	private static final int CODIGO_ERROR_CONEXION_SERVIDOR= 0;
	private static final String ERROR_CONEXION_SERVIDOR = "Ha ocurrido un error al intentar conectarse al servidor.";
	private static final int CODIGO_ERROR_NOMBRE_USUARIO = 1044;
	private static final String ERROR_NOMBRE_USUARIO = "El nombre de usuario especificado no existe.";
	private static final int CODIGO_ERROR_CONTRASENIA = 1045;	
	private static final String ERROR_CONTRASENIA = "La contrasenia especificada no coincide con la del usuario";
	
        private AdminConexiones(){
            
        }
        
        public static AdminConexiones obtenerAdminConexiones(){
            if(adminConexiones==null){
                adminConexiones = new AdminConexiones();
            }
            return adminConexiones;
        }
	public Connection getConnection() throws ConexionBDException  {
		Connection conexion = null;
		try {
			Class.forName( DatosConexion.obtenerDatosConexion().getControlador() );
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			conexion=DriverManager.getConnection( DatosConexion.obtenerDatosConexion().getServidor() +":"+
					DatosConexion.obtenerDatosConexion().getPuerto()+"/"+
					DatosConexion.obtenerDatosConexion().getNombreBD(), DatosConexion.obtenerDatosConexion().getUsuario(),
					DatosConexion.obtenerDatosConexion().getContrasenia());
		} catch (SQLException e) {
			switch(e.getErrorCode()){
			case CODIGO_ERROR_CONEXION_SERVIDOR:
				log.error(ERROR_CONEXION_SERVIDOR);
				throw new ConexionBDException(ERROR_CONEXION_SERVIDOR);
			case CODIGO_ERROR_NOMBRE_USUARIO:
				log.warn(ERROR_NOMBRE_USUARIO);
				throw new ConexionBDException(ERROR_NOMBRE_USUARIO);
			case CODIGO_ERROR_CONTRASENIA:
				log.warn(ERROR_CONTRASENIA);
				throw new ConexionBDException(ERROR_CONTRASENIA);
			}
			e.printStackTrace();
		}
        
	return (Connection) conexion;		
	}

}
