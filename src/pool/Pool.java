/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

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
