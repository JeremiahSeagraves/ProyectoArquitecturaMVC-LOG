/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author miguelangel
 */
public class PoolConnection {
    private boolean active;
    private Connection connection;
    
    public PoolConnection(Connection connection){
        this.active = true;
        this.connection = connection;
    }
    
    public ResultSet recoveryQuery(String query) throws SQLException{
        ResultSet result = null;
        Statement sentencia = connection.createStatement();
        result = sentencia.executeQuery(query);
        return result; 
    }
    
    public void insertQuery(String query) throws SQLException{
        Statement sentencia = connection.createStatement();
        sentencia.executeUpdate(query);
    }
    
    public void close(){
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }
    
    public void activate(){
        this.active = true;
    }
}
