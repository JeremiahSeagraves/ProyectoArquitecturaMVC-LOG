package archivo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import MVC.modelo.DatosConexion;

public class ParserXML {
	private String ruta = null;

	public ParserXML(String ruta) {
		this.ruta = ruta;
	}

	public void cargarXml() {
		System.out.println("cargando xml");
		// Se crea un SAXBuilder para poder parsear el archivo
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(ruta);
		try {
			// Se crea el documento a traves del archivo
			Document document = (Document) builder.build(xmlFile);

			// Se obtiene la raiz 'database'
			Element rootNode = document.getRootElement();

			// Se obtiene la lista de hijos de la raiz 'database'
			List<?> list = rootNode.getChildren("config");

			// Se recorre la lista de hijos de 'database'
			for (int i = 0; i < list.size(); i++) {
				// Se obtiene el elemento 'config'
				Element database = (Element) list.get(i);

				// Se obtiene el atributo 'name' que esta en el tag 'database'
				String nombreDatabase = database.getAttributeValue("name");

				System.out.println("Database: " + nombreDatabase);

				System.out.println("\tControlador\t\tServidor\t\tPuerto\t\tUsuario\t\tContrasenia");

				// Se obtienen los valores que estan entre los tags
				// '<config></config>'
				// Se obtiene el valor que esta entre los tags
				// '<controller></controller>'
				String controlador = database.getChildTextTrim("controller");

				// Se obtiene el valor que esta entre los tags
				// '<serverurl></serverurl>'
				String urlservidor = database.getChildTextTrim("serverurl");

				// Se obtiene el valor que esta entre los tags '<port></port>'
				String puerto = database.getChildTextTrim("port");

				// Se obtiene el valor que esta entre los tags
				// '<username></username>'
				String usuario = database.getChildTextTrim("username");

				// Se obtiene el valor que esta entre los tags
				// '<password></password>'
				String contrasenia = database.getChildTextTrim("password");

				modificarDatosConexion(nombreDatabase, controlador, urlservidor, puerto, usuario, contrasenia);
			}
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException jdomex) {
			jdomex.printStackTrace();
		}
	}

	private void modificarDatosConexion(String nombreBD, String controlador, String urlservidor, String puerto,
			String usuario, String contrasenia) {
		DatosConexion.obtenerDatosConexion().setNombreBD(nombreBD);
		DatosConexion.obtenerDatosConexion().setControlador(controlador);
		DatosConexion.obtenerDatosConexion().setServidor(urlservidor);
		DatosConexion.obtenerDatosConexion().setPuerto(puerto);
		DatosConexion.obtenerDatosConexion().setUsuario(usuario);
		DatosConexion.obtenerDatosConexion().setContrasenia(contrasenia);
	}

}
