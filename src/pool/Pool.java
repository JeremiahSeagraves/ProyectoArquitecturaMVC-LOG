/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

import java.util.logging.Level;
import java.util.logging.Logger;
import MVC.excepciones.NotAvailableConnectionsException;

/**
 *
 * @author miguelangel
 */
public class Pool {
    public static void main(String[] args) {
        String ruta = "src/pool/archivoConfiguracion.xml";
        MonitorArchivoConfiguracion monitorArchivo =
                new MonitorArchivoConfiguracion("Pool", ruta);
        monitorArchivo.start();                        
    }
}
