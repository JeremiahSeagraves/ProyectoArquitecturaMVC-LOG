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
import MVC.excepciones.ArchivoConfiguracionNoEncontradoExcepcion;
import MVC.excepciones.MapeoErroneoExcepcion;
import MVC.excepciones.MapeoInexistenteExcepcion;
import MVC.excepciones.ObjetoErroneoExcepcion;
import pool.PoolConnection;

/**
 *
 * @author Noe
 *//**
 *
 * @author Noe
 */
public class MapeadorObjetoRelacional {

    private ArrayList listaObjetos;
    private MapeoClaseTabla[] mapeos;

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
            ResultSet datosTabla = conexion.query(consulta);
            
            GeneradorObjetos generador = new GeneradorObjetos(mapeoEncontrado, datosTabla);
            generador.generarObjetos();
            listaObjetos = generador.getListaObjetos();
        }else{
            throw new MapeoInexistenteExcepcion("No existe un mapeo para la clase: "+ URLClase);
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
