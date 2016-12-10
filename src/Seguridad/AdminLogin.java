/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import java.util.Timer;

/**
 *
 * @author Angel Basto Gonzalez
 */
public class AdminLogin {
    private final int NUM_MAX_INTENTOS = 5;
    private int numIntentos = 1;
    private static AdminLogin adminLogin;
    
    public boolean bloqueado = false;
    
    private UsuariosDao usuariosDao;
    
    private AdminLogin() {
        this.usuariosDao = new UsuariosDao();
    }
    
    public static AdminLogin obtenerAdminLogin(){
        if(adminLogin==null){
            adminLogin = new AdminLogin();
        }
        return adminLogin;
    }

    public int getNumIntentos() {
        return numIntentos;
    }

    public void setNumIntentos(int numIntentos) {
        this.numIntentos = numIntentos;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }
    
    public boolean validarAccesoAlSistema(String nombreUsuario, String contraseña){
            if(!isBloqueado()){
                if(obtenerPermiso()){
                    if (validarUsuario(nombreUsuario)) {
                        if (validarContrasenia(nombreUsuario, contraseña)) {
                            System.out.println("Acceso autorizado");
                            return true;
                        } else {
                            System.out.println("###Contraseña incorrecta###");
                            adminLogin.setNumIntentos(numIntentos+1);
                        }
                    } else {
                        System.out.println("###Usuario no encontrado###");
                        adminLogin.setNumIntentos(numIntentos+1);
                    }  
                }else{
                    bloquearSistema();
                }
            }else{
                System.out.println("#########################");
                System.out.println("###*Sistema bloqueado*###");
                System.out.println("#########################");
            }           
        return false;
    }
    
    private boolean validarUsuario(String usuario){
        if(usuariosDao.obtenerUsuario(usuario)!=null){
            return true;
        }
        return false;
    }
    
    private boolean validarContrasenia(String nombreUsuario, String contrasenia){
        Usuario usuario = usuariosDao.obtenerUsuario(nombreUsuario);
        if(usuario.getContrasenia().equals(contrasenia)){
            return true;
        }
        return false;
    }
    
    public void bloquearSistema(){
        System.out.println("#########################");
        System.out.println("###*Sistema bloqueado*###");
        System.out.println("#########################");
        adminLogin.setBloqueado(true);
        Timer timer = new Timer();        
        timer.schedule(new Contador(timer), 0, 1000);
    }
    
    public static void desbloquearSistema(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("###*Sistema Desbloqueado*###");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        adminLogin.setBloqueado(false);
        adminLogin.setNumIntentos(0);
    }
    
    public boolean obtenerPermiso(){
        return adminLogin.getNumIntentos()<NUM_MAX_INTENTOS;
    }
}
