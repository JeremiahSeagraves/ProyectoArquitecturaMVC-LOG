package mapeo.configuracion;

import java.io.File;
import java.io.IOException;
import java.util.List;
import mapeo.MapeoClaseTabla;
import mapeo.excepcion.ArchivoConfiguracionNoEncontradoExcepcion;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ParseadorConfiguracionMapeo {

    public ParseadorConfiguracionMapeo() {

    }

    public MapeoClaseTabla[] parsear(String ruta) throws ArchivoConfiguracionNoEncontradoExcepcion {
        MapeoClaseTabla[] configuracion = null ;
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        //Se crea un archivo con la ruta del archivo xml
        File xmlFile = new File(ruta);
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);

            //Se obtiene la raiz 'map'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de los mapeos
            List<?> list = rootNode.getChildren("class");
            
            //Crea un array con tamaño igual al numero de mapeos
            configuracion = new MapeoClaseTabla[list.size()];
            

            //Se recorre la lista de los mapeos
            for (int numeroMapeo = 0; numeroMapeo < list.size(); numeroMapeo++) {
                //Se obtiene el elemento 'class'
                Element clase = (Element) list.get(numeroMapeo);
                
                //Se crea el nuevo mapeo
                MapeoClaseTabla nuevoMapeo = new MapeoClaseTabla();
                
                //Se obtiene el nombre de la clase
                String nombreClase = clase.getAttributeValue("name");
                nuevoMapeo.setNombreClase(nombreClase);
                
                //Se obtiene el elemento que contiene los datos de la tabla
                Element tabla = clase.getChild("table");
                
                //Se obtiene el nombre de la tabla
                String nombreTabla = tabla.getAttributeValue("name");
                nuevoMapeo.setNombreTabla(nombreTabla);
                
                //Se obtiene todos las relaciones entre atributos y campos
                List<?> listaRelaciones = clase.getChildren("relation");
                
            
                for (int numeroRelacion= 0; numeroRelacion < listaRelaciones.size(); numeroRelacion++) {
                    Element relacion = (Element) listaRelaciones.get(numeroRelacion);
                    String nombreAtributo = relacion.getAttributeValue("attribute");
                    String nombreCampo = relacion.getAttributeValue("field");
                    nuevoMapeo.agregar(nombreAtributo, nombreCampo);
                }
                
                configuracion[numeroMapeo] = nuevoMapeo;
            }
        } catch (IOException | JDOMException io) {
            throw new ArchivoConfiguracionNoEncontradoExcepcion("No encontrado Archivo de configuracion: "+ ruta);
        }
        
        return configuracion;
    }

}
