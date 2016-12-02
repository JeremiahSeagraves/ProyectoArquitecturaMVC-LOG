package MVC.enlace;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Parser {

    private ArrayList<Relacion> relaciones;
    private String archivoConfiguracion;
    private static Logger log = Logger.getLogger(Parser.class);

    public Parser() {
        relaciones = new ArrayList();
        archivoConfiguracion = null;
    }

    public void obtenerEnlaces() {
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        File archivoXML = new File(archivoConfiguracion);
        try {
            //Se crea el documento a traves del archivo
            Document documento = (Document) builder.build(archivoXML);
            //Se obtiene la raiz 'operaciones'
            Element nodoClases = documento.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'operaciones'
            List list = nodoClases.getChildren("operacion");

            //Se recorre la lista de hijos de 'operaciones'
            for (int i = 0; i < list.size(); i++) {
                Element operacion = (Element) list.get(i);
                String nombreOperacion = operacion.getAttributeValue("nombre");
                String controlador = operacion.getAttributeValue("controlador");
                String metodo = operacion.getAttributeValue("metodo");
                
                //Se crea la Relacion entre las operaciones de vista y el controlador especifico
                Relacion relacion = new Relacion(nombreOperacion, controlador, metodo);
                relaciones.add(relacion);
            }
        } catch (IOException | JDOMException io) {
            log.error(io.getMessage());
            System.out.println(io.getMessage());
        }
    }

    public Relacion obtenerRelacion(String operacion) {
        for (int i = 0; i < relaciones.size();i++) {
            if (relaciones.get(i).getOperacion().equals(operacion)) {
                return relaciones.get(i);
            }
        }
        return null;
    }

    public void setArchivConfiguracion(String archivo) {
        this.archivoConfiguracion = archivo;
    }
}
