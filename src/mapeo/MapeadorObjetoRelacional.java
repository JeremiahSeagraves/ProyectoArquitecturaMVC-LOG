/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeo;

import java.lang.reflect.InvocationTargetException;
import mapeo.configuracion.ParseadorConfiguracionMapeo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mapeo.excepcion.ArchivoConfiguracionNoEncontrado;
import mapeo.excepcion.MapeoErroneoExcepcion;
import mapeo.excepcion.MapeoInexistenteExcepcion;
import mapeo.excepcion.ObjetoErroneoExcepcion;
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

    public MapeadorObjetoRelacional(String URLArchivoConfiguracion) throws ArchivoConfiguracionNoEncontrado {
        iniciarRelacioTablaClase(URLArchivoConfiguracion);
    }

    private void iniciarRelacioTablaClase(String URLArchivoConfiguracion) throws ArchivoConfiguracionNoEncontrado {
        ParseadorConfiguracionMapeo parseador = new ParseadorConfiguracionMapeo();
        mapeos = parseador.parsear(URLArchivoConfiguracion);
    }

    public void mapearObjetosRelacion(String URLClase, PoolConnection conexion) throws MapeoInexistenteExcepcion, ObjetoErroneoExcepcion, MapeoErroneoExcepcion, SQLException  {

        MapeoClaseTabla mapeo = encontrarMapeo(URLClase);
        if (mapeo != null) {
            String consulta = "SELECT * FROM " + mapeo.getNombreTabla();
            ResultSet datosTabla = conexion.query(consulta);
            GeneradorObjetos generador = new GeneradorObjetos(mapeo, datosTabla);
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
