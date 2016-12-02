package modelo;

public class DatosConexion {
	private String nombreBD;
	private String controlador;
	private String servidor;
	private String puerto;
	private String usuario;
	private String contrasenia;
	private static DatosConexion datosConexion=null;
	
	private DatosConexion() {
		
	}
	
	public static DatosConexion obtenerDatosConexion(){
		if(datosConexion==null){
			datosConexion = new DatosConexion();
		}
		return datosConexion;
	}
	
	public String getNombreBD() {
		return nombreBD;
	}

	public void setNombreBD(String nombreBD) {
		this.nombreBD = nombreBD;
	}

	public String getControlador() {
		return controlador;
	}

	public void setControlador(String controlador) {
		this.controlador = controlador;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	
	
	
	
}
