/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

/**
 *
 * @author Angel Basto Gonzalez
 */
public class Contraseña {
    
    public boolean validarRequerimientosContraseña(String contraseña){
        
        if(tieneAlMenosOchoCaracteres(contraseña)){
            if(tieneAlMenosUnaMayuscula(contraseña)){
                if(tieneAlMenosUnaMinuscula(contraseña)){
                    if(tieneAlMenosUnNumero(contraseña)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean tieneAlMenosOchoCaracteres(String contraseña){
        return 8<=contraseña.length();
    }
    
    private boolean tieneAlMenosUnaMayuscula(String contraseña){
        int i = 0;
        while(i < contraseña.length()){
            if(65<=contraseña.charAt(i) && contraseña.charAt(i)<=90){
                return true;
            }
            i++;
        }
        return false;
    }
    
    private boolean tieneAlMenosUnaMinuscula(String contraseña){
        int i = 0;
        while(i < contraseña.length()){
            if(97<=contraseña.charAt(i) && contraseña.charAt(i)<=122){
                return true;
            }
            i++;
        }
        return false;
    }
    
    private boolean tieneAlMenosUnNumero(String contraseña){
        int i = 0;
        while(i < contraseña.length()){
            if(48<=contraseña.charAt(i) && contraseña.charAt(i)<=57){
                return true;
            }
            i++;
        }
        return false;
    }
}
