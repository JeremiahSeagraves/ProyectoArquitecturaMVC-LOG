/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel Basto Gonzalez
 */
public class Test {
    public static void main(String[] args) {
        Scanner entradaString = new Scanner(System.in);
        boolean accesoSistema=false;
        Contraseña contraseña = new Contraseña();
        
//        do{
//            System.out.println("Introduce tu usuario: ");
//            String nombreUsuario = entradaString.nextLine();
//            System.out.println("Introduce tu contraseña: ");
//            String contraseña = entradaString.nextLine();
//        
//            try {
//                String contraseñaEncriptada = Encriptacion.md5(contraseña);
//                //System.out.println(contraseñaEncriptada);
//                accesoSistema = AdminLogin.obtenerAdminLogin().validarAccesoAlSistema(nombreUsuario, contraseñaEncriptada);
//                System.out.println(" ");
//            } catch (Exception ex) {
//                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }while(!accesoSistema);
do{
        System.out.println("Introduce una contraseña");
        System.out.println("*Debe tener al menos 8 caracteres");
        System.out.println("*Debe tener al menos una mayuscula");
        System.out.println("*Debe tener al menos una minuscula");
        System.out.println("*Debe tener al menos un numero");
        String contra = entradaString.nextLine();
        
        accesoSistema = contraseña.validarRequerimientosContraseña(contra);
        System.out.println(accesoSistema);
}while(!accesoSistema);
        
   }
}
