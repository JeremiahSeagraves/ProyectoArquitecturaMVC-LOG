/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel Basto Gonzalez
 */
public class UsuariosDao {
    private ConectorBD conectorBD;

    public UsuariosDao() {
        this.conectorBD = new ConectorBD();
    }
    
    public Usuario obtenerUsuario(String nombreUsuario){
        Usuario usuario = null;
        
        try {
            conectorBD.conectar();
            
            String consulta = "select * from Usuarios where NombreUsuario = ?";
            PreparedStatement declaracion = conectorBD.consulta(consulta);
            declaracion.setString(1, nombreUsuario);
            
            ResultSet resultado = declaracion.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario(resultado.getString("NombreUsuario"), resultado.getString("Contraseña"));
            }
            
            conectorBD.desconectar();
            
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Usuario no encontrado");
        }
        return usuario;
    }
}
