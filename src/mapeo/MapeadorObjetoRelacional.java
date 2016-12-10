/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeo;

import mapeo.configuracion.ParseadorConfiguracionMapeo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import excepciones.ArchivoConfiguracionNoEncontradoExcepcion;
import excepciones.MapeoErroneoExcepcion;
import excepciones.MapeoInexistenteExcepcion;
import excepciones.ObjetoErroneoExcepcion;
import org.apache.log4j.Logger;
import pool.PoolConnection;

/**
 *
 * @author Noe
 */
public class MapeadorObjetoRelacional {

    private ArrayList listaObjetos;
    private MapeoClaseTabla[] mapeos;
    private static final String MAPEO_INEXISTENTE ="No existe un mapeo para la clase: " ;

    public MapeadorObjetoRelacional(String URLArchivoConfiguracion) throws ArchivoConfiguracionNoEncontradoExcepcion {
        obtenerMapeos(URLArchivoConfiguracion);
    }

    private void obtenerMapeos(String URLArchivoConfiguracion) throws ArchivoConfiguracionNoEncontradoExcepcion {
        ParseadorConfiguracionMapeo parseador = new ParseadorConfiguracionMapeo();
        mapeos = parseador.parsear(URLArchivoConfiguracion);
    }

    public void mapearObjetosRelacion(String URLClase, PoolConnection conexion) throws MapeoInexistenteExcepcion, ObjetoErroneoExcepcion, MapeoErroneoExcepcion, SQLException  {

        MapeoClaseTabla mapeoEncontrado = encontrarMapeo(URLClase);
        
        if (mapeoEncontrado != null) {
            String consulta = "SELECT * FROM " + mapeoEncontrado.getNombreTabla();
            ResultSet datosTabla = conexion.recoveryQuery(consulta);
            
            GeneradorObjetos generador = new GeneradorObjetos(mapeoEncontrado, datosTabla);
            generador.generarObjetos();
            listaObjetos = generador.getListaObjetos();
        }else{
            throw new MapeoInexistenteExcepcion(MAPEO_INEXISTENTE+ URLClase);
        }

    }

    private MapeoClaseTabla encontrarMapeo(String clase) {
        
         for (int j = 0; j < mapeos.length; j++) {

            if ((mapeos[j].getNombreClase()).equals(clase)) {
                return mapeos[j];
            }

        }
        return null;

    }

    public ArrayList getListaObjetos() {
        return listaObjetos;
    }

}
