package mapeo.configuracion;

import java.io.File;
import java.io.IOException;
import java.util.List;
import mapeo.MapeoClaseTabla;
import mapeo.excepcion.ArchivoConfiguracionNoEncontrado;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ParseadorConfiguracionMapeo {

    public ParseadorConfiguracionMapeo() {

    }

    public MapeoClaseTabla[] parsear(String ruta) throws ArchivoConfiguracionNoEncontrado {
        MapeoClaseTabla[] configuracion = null ;
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta);
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);

            //Se obtiene la raiz 'map'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'map'
            List<?> list = rootNode.getChildren("class");
            configuracion = new MapeoClaseTabla[list.size()];
            

            //Se recorre la lista de hijos de 'map'
            for (int i = 0; i < list.size(); i++) {
                //Se obtiene el elemento 'class'
                Element clase = (Element) list.get(i);

                //Se obtiene el atributo 'name' que esta en el tag 'class'
                String nombreClase = clase.getAttributeValue("name");
                MapeoClaseTabla nuevaRelacion = new MapeoClaseTabla();
                nuevaRelacion.setNombreClase(nombreClase);
                
                Element tabla = clase.getChild("table");
                
                //Se obtiene el atributo 'name' que esta en el tag 'class'
                String nombreTabla = tabla.getAttributeValue("name");
                nuevaRelacion.setNombreTabla(nombreTabla);
                
                List<?> listaRelaciones = clase.getChildren("relation");
                for (int j = 0; j < listaRelaciones.size(); j++) {
                    Element relacion = (Element) listaRelaciones.get(j);
                    String nombreAtributo = relacion.getAttributeValue("attribute");
                    String nombreCampo = relacion.getAttributeValue("field");
                    nuevaRelacion.add(nombreAtributo, nombreCampo);
                }
                
                configuracion[i] = nuevaRelacion;

            }
        } catch (IOException | JDOMException io) {
            throw new ArchivoConfiguracionNoEncontrado("No encontrado Archivo de configuracion: "+ ruta);
        }
        
        return configuracion;
    }

}
