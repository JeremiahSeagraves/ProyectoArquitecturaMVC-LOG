/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapeo.excepcion.CampoTablaNoExiste;
import mapeo.excepcion.MetodoSetNoEncontrado;

/**
 *
 * @author Noe
 */
public class GeneradorObjetos {

    private ArrayList listaObjetos;
    private RelacionClaseTabla relacion;
    private ResultSet tabla;

    public GeneradorObjetos(RelacionClaseTabla relacion, ResultSet tabla) {
        this.relacion = relacion;
        this.tabla = tabla;
        listaObjetos = new ArrayList();
    }

    public void generarObjetos() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Class nuevaClase = Class.forName(relacion.getNombreClase());

        while (tabla.next()) {
            Object nuevoObjeto = nuevaClase.newInstance();
            inicializarPorMetodoSet(nuevoObjeto);

        }

    }

    private boolean inicializarPorMetodoSet(Object nuevoObjeto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, NoSuchMethodException {
        boolean bandera = false;
        Field[] atributosClase = nuevoObjeto.getClass().getDeclaredFields();
        for (int i = 0; i < atributosClase.length; i++) {
            String nombreCampo = relacion.getCampo(atributosClase[i].getName());
            if (nombreCampo != null) {
                inicializarAtributo(nombreCampo, nuevoObjeto, atributosClase[i]);
            }
        }
        return bandera;

    }

    private void inicializarAtributo(String campo, Object objeto, Field atributo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, NoSuchMethodException {
        
        String nombreMetodoSet = getNombreMetodoSet(atributo, objeto);
        Class[] parametrosMetodoSet = {atributo.getType()};
        Method metodoSet = null;
        metodoSet = objeto.getClass().getDeclaredMethod(nombreMetodoSet, parametrosMetodoSet);
        metodoSet.invoke(objeto, tabla.getObject(campo));

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
