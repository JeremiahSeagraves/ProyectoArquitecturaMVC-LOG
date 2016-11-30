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

/**
 *
 * @author Noe
 */
public class MapeadorObjetoRelacional {

    private ArrayList listaObjetos;
    private RelacionClaseTabla[] relaciones;

    public MapeadorObjetoRelacional(String URLArchivoConfiguracion) {
        iniciarRelacioTablaClase(URLArchivoConfiguracion);
    }

    private void iniciarRelacioTablaClase(String URLArchivoConfiguracion) {
        ParseadorConfiguracionMapeo parseador = new ParseadorConfiguracionMapeo();
        relaciones = parseador.parsear(URLArchivoConfiguracion);
    }

    public void mapearObjetosRelacion(String URLClase, PoolConnection conexion) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

        for (int j = 0; j < relaciones.length; j++) {

            if ((relaciones[j].getNombreClase()).equals(URLClase)) {
                String consulta = "SELECT * FROM " + relaciones[j].getNombreTabla();
                ResultSet datosTabla = conexion.query(consulta);
                GeneradorObjetos generador = new GeneradorObjetos(relaciones[j], datosTabla);
                generador.generarObjetos();
                listaObjetos = generador.getListaObjetos();
            }

        }

    }

    public ArrayList getListaObjetos() {
        return listaObjetos;
    }

}
