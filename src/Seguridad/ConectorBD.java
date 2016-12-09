/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Angel Basto
 */
public class ConectorBD {
    
    private Connection conexion;
    
    public void conectar() throws SQLException{
        conexion = DriverManager.getConnection("jdbc:sqlite:DatosUsuarios.db");
    }
    
    public void desconectar() throws SQLException{
        conexion.close();
    }
    
   public PreparedStatement consulta(String consulta) throws SQLException{
        return this.conexion.prepareStatement(consulta);
    }
}

