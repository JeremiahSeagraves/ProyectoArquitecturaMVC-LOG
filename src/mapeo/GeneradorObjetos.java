/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeo;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.MapeoErroneoExcepcion;
import excepciones.ObjetoErroneoExcepcion;
import org.apache.log4j.Logger;

/**
 *
 * @author Noe
 */
public class GeneradorObjetos {

    private ArrayList listaObjetos;
    private MapeoClaseTabla mapeo;
    private ResultSet tabla;

    public GeneradorObjetos(MapeoClaseTabla mapeo, ResultSet tabla) {
        this.mapeo = mapeo;
        this.tabla = tabla;
        listaObjetos = new ArrayList();
    }

    public void generarObjetos() throws ObjetoErroneoExcepcion, MapeoErroneoExcepcion, SQLException {
        try {
            Class nuevaClase = Class.forName(mapeo.getNombreClase());
            Object nuevoObjeto = nuevaClase.newInstance();
            while (tabla.next()) {
                inicializarObjeto(nuevoObjeto);
                listaObjetos.add(nuevoObjeto);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new ObjetoErroneoExcepcion("No se puede instanciar el objeto: " + mapeo.getNombreClase());
        }

    }

    private void inicializarObjeto(Object nuevoObjeto) throws MapeoErroneoExcepcion, ObjetoErroneoExcepcion {

        Field[] atributosClase = nuevoObjeto.getClass().getDeclaredFields();
        
        //Inicializa cada atributo de la clase
        for (int i = 0; i < atributosClase.length; i++) {
            String nombreCampo = mapeo.getCampo(atributosClase[i].getName());
            if (nombreCampo != null) {
                inicializarAtributo(nombreCampo, nuevoObjeto, atributosClase[i]);
            }
        }

    }

    private void inicializarAtributo(String campo, Object objeto, Field atributo) throws MapeoErroneoExcepcion, ObjetoErroneoExcepcion {

        try {
            String nombreMetodoSet = getNombreMetodoSet(atributo, objeto);
            Class[] parametrosMetodoSet = {atributo.getType()};
            Method metodoSet = null;
            metodoSet = objeto.getClass().getDeclaredMethod(nombreMetodoSet, parametrosMetodoSet);
            metodoSet.invoke(objeto, tabla.getObject(campo));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ObjetoErroneoExcepcion("No se encontro el metodo para iniciar el atributo:" + campo);
        } catch (SQLException ex) {
            throw new MapeoErroneoExcepcion("La tabla en la Base de Datos no contiene : " + campo);
        }

    }

    private String getNombreMetodoSet(Field atributo, Object objeto) {

        String nombreMetodo = "set" + atributo.getName();
        Method[] metodos = objeto.getClass().getMethods();
        
        for (int i = 0; i < metodos.length; i++) {
            if ((metodos[i].getName().toLowerCase()).contains(nombreMetodo.toLowerCase())) {
                return metodos[i].getName();
            }
        }
        return null;

    }

    public ArrayList getListaObjetos() {
        return listaObjetos;
    }
}
