/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.vista;

import MVC.excepciones.ClaseNoEncontrada;
import MVC.excepciones.FalloInstanciaDeClase;
import MVC.excepciones.NoEsSubclaseControlador;
import MVC.excepciones.NoSePuedeAccederAlaClase;
import MVC.vista.Vista;
import MVC.vista.Evento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milka
 */
public class VistaPrueba extends Vista {

    Ventana ventana;

    public static void main(String[] args) {
        VistaPrueba v = new VistaPrueba();
    }

    public VistaPrueba() {
        ventana = new Ventana();
        ventana.setVisible(true);
        guardarNombre();
        setConfiguracion("archivoConfiguracionDefault.xml");
    }

    public void guardarNombre() {
        ventana.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Evento e = new Evento("controlador.ControladorPrueba", "operacion1", ventana.getField().getText());
                try {
                    callService(e);
                } catch (ClaseNoEncontrada ex) {
                    Logger.getLogger(VistaPrueba.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoEsSubclaseControlador ex) {
                    Logger.getLogger(VistaPrueba.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FalloInstanciaDeClase ex) {
                    Logger.getLogger(VistaPrueba.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSePuedeAccederAlaClase ex) {
                    Logger.getLogger(VistaPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
