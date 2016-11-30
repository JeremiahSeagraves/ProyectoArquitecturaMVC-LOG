/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                callService(e);
            }
        });
    }

}
