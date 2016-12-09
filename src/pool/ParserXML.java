/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import MVC.excepciones.ConnectionsInUseException;
import MVC.excepciones.ErrorPoolConfigException;

/**
 *
 * @author miguelangel
 */
public class ParserXML {

    private String ruta;

    private Document document;
    

    public ParserXML(String ruta) {
        this.ruta = ruta;
    }

    public void cargarXml() throws ErrorPoolConfigException {
        //System.out.println("cargando xml");
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();

        File xmlFile = new File(ruta);
        try {
            //Se crea el documento a traves del archivo
            document = (Document) builder.build(xmlFile);
            
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz
            List<?> list = rootNode.getChildren("config");

            //Se recorre la lista de hijos 
            for (int i = 0; i < list.size(); i++) {
                //Se obtiene el elemento 'config'
                Element segment = (Element) list.get(i);

                //segment = segment.getChildText("segment");
                //System.out.println("number of segment: " + numberOfsegment);

                //Se obtiene el atributo 'connections' que esta en el tag 'connections'
                int connections =Integer.parseInt(segment.getChildText("connections"));
                AdminPool.changeNumberOfPoolConnections(connections);                
            }
        } catch (IOException | JDOMException jdomex) {
            String MENSAJE = "Error al configurar el Pool";
            throw new ErrorPoolConfigException(MENSAJE);
        } 
    }
}
