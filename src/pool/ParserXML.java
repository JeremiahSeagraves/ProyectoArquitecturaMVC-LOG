/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

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

    public void cargarXml() {
        //System.out.println("cargando xml");
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();

        File xmlFile = new File(ruta);
        try {

            //Se crea el documento a traves del archivo
            document = (Document) builder.build(xmlFile);

            //Se obtiene la raiz 'database'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz
            List<?> list = rootNode.getChildren("config");

            //Se recorre la lista de hijos 
            for (int i = 0; i < list.size(); i++) {
                //Se obtiene el elemento 'config'
                Element segment = (Element) list.get(i);

                String numberOfsegment = segment.getChildText("segment");
                //System.out.println("number of segment: " + numberOfsegment);

                //Se obtiene el atributo 'connections' que esta en el tag 'connections'
                int connections =Integer.parseInt(segment.getChildText("connections"));
                AdminPool.changeNumberOfPoolConnections(connections);
                //System.out.println("Number of connections: " + connections);
            }
        } catch (IOException io) {
            io.printStackTrace();
        } catch (JDOMException jdomex) {
            jdomex.printStackTrace();
        }
    }
}
