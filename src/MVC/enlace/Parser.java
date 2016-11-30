package enlace;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Parser {

    private ArrayList<Relacion> relaciones;
    private String archivoConfiguracion;

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
            //Se obtiene la raiz 'clases'
            Element nodoClases = documento.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'tables'
            List list = nodoClases.getChildren("clase");

            //Se recorre la lista de hijos de 'clases'
            for (int i = 0; i < list.size(); i++) {
                //Se obtiene el elemento 'clase'
                Element clase = (Element) list.get(i);

                //Se obtiene el atributo 'nombre' que esta en el tag 'clase'
                String nombreClaseCtrl = clase.getAttributeValue("nombre");

                //Se obtiene la lista de hijos del tag 'tabla'
                List lista_enlaces = clase.getChildren();

                //Se crea la Relacion entre las operaciones de vista y el controlador especifico
                Relacion relacion = new Relacion(nombreClaseCtrl, lista_enlaces.size());

                //Se recorre la lista de campos
                int j = 0;
                for (Object lista_enlace : lista_enlaces) {
                    //Se obtiene el elemento 'campo'
                    Element campo = (Element) lista_enlace;
                    //Se obtienen los valores que estan entre los tags '<enlace></enlace>'
                    //Se obtiene el valor que esta entre los tags '<operacion></operacion>'
                    String operacionView = campo.getChildTextTrim("operacion");
                    //Se obtiene el valor que esta entre los tags '<metodoCtrl></metodoCtrl>'
                    String operacionCtrl = campo.getChildTextTrim("metodoCtrl");
                    
                    relacion.setRelacion(operacionView, operacionCtrl, j);
                    j++;
                    
                    relaciones.add(relacion);
                }
               
            }
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
    }

    public String obtenerOperacionControlador(String clase, String operacion) {
        for (int i = 0; i < relaciones.size();i++) {
            if (relaciones.get(i).controlador.equals(clase)) {
                return relaciones.get(i).getOperacionControlador(operacion);
            }
        }
        return null;
    }

    public void setArchivConfiguracion(String archivo) {
        this.archivoConfiguracion = archivo;
    }
}
