/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.vista;

import MVC.excepciones.ArgumentosNoCorrectos;
import MVC.excepciones.ClaseNoEncontrada;
import MVC.excepciones.ErrorAlInvocarObjetivo;
import MVC.excepciones.FalloInstanciaDeClase;
import MVC.excepciones.MetodoNoExiste;
import MVC.excepciones.NoEsSubclaseControlador;
import MVC.excepciones.NoSePuedeAccederAlaClase;
import MVC.excepciones.ViolacionDeSeguridad;
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

    ClaseNoEncontrada claseNoEncontrada = new ClaseNoEncontrada();

    public static void main(String[] args) {
        VistaPrueba v = new VistaPrueba();
    }

    public VistaPrueba() {
        ventana = new Ventana();
        ventana.setVisible(true);
        guardarNombre();
        setConfiguracion("C:\\Users\\Milka\\Documents\\NeatBeansProjects\\ProyectoArquitecturaMVC-LOGIN\\archivoConfiguracionDefault.xml");
    }

    public void guardarNombre() {
        ventana.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Evento e = new Evento("update", ventana.getField().getText());
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
                } catch (MetodoNoExiste ex) {

                    Logger.getLogger(VistaPrueba.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ViolacionDeSeguridad ex) {

                    Logger.getLogger(VistaPrueba.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArgumentosNoCorrectos ex) {

                    Logger.getLogger(VistaPrueba.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ErrorAlInvocarObjetivo ex) {

                    Logger.getLogger(VistaPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
