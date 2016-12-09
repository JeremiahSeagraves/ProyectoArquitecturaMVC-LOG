/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

import excepciones.ConexionBDException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import excepciones.ConnectionsInUseException;
import excepciones.NotAvailableConnectionsException;
import excepciones.ConnectionsInUseException;
import excepciones.NotAvailableConnectionsException;
import conexiones.AdminConexiones;

/**
 *
 * @author miguelangel
 */
public class AdminPool {  
    private static AdminPool adminPool = null;   
    private static ArrayList<PoolConnection> availablePoolConnections;
    private static ArrayList<PoolConnection> unavailablePoolConnections;
    private AdminConexiones adminConexiones;
    //private ParserXML parser;
    
    private static int numberOfConnections;
    
    private final static String NOT_AVAILABLE_CONNECTIONS = "No Hay Conexiones Disponibles";
    private final static String IMPOSSIBLE_REDUCE_CONNECTIONS = "Imposible reducir "
            + "el número de conexiones. Hay conexiones en uso";
    
    private AdminPool(){
        adminConexiones = new AdminConexiones();
        availablePoolConnections = new ArrayList();
        unavailablePoolConnections = new ArrayList();
    }
    
    public static AdminPool getInstance(){
        if(adminPool == null){
            adminPool = new AdminPool();
        }        
        return adminPool;
    }
    
    public void initializePoolConnections(){
        createPoolConnections(numberOfConnections);
    }
    
    public PoolConnection getPoolConnection() throws NotAvailableConnectionsException{
        if(availablePoolConnections.size()>0){
            for (int i = 0; i < availablePoolConnections.size(); i++) {
                if(availablePoolConnections.get(i).isActive()){
                    unavailablePoolConnections.add(availablePoolConnections.get(i));
                    return availablePoolConnections.get(i);
                }
            }
        }else{
            throw new NotAvailableConnectionsException(NOT_AVAILABLE_CONNECTIONS);
        }
           
        return null;
    }
    
    public void closeConnection(PoolConnection pc){
        availablePoolConnections.remove(pc);        
        pc.close();
        availablePoolConnections.add(pc);                
        unavailablePoolConnections.remove(pc);
        
        updatePoolConnections();
    }
    
    public void updatePoolConnections(){
        for (int i = 0; i < availablePoolConnections.size(); i++) {
            if(!availablePoolConnections.get(i).isActive()){
                availablePoolConnections.get(i).activate();
            }
        }
    }
    
    private void createPoolConnections(int numberOfConnections) {
        for (int i = 0; i < numberOfConnections; i++) {
            Connection connection = null;
            try {
                connection = adminConexiones.getConnection();
            } catch (ConexionBDException ex) {
                Logger.getLogger(AdminPool.class.getName()).log(Level.SEVERE, null, ex);
            }
            PoolConnection pc = new PoolConnection(connection);
            availablePoolConnections.add(pc);
        }
    }
    
    public void changePoolConnections() throws ConnectionsInUseException{
        if(numberOfConnections < unavailablePoolConnections.size()){
            throw new ConnectionsInUseException(IMPOSSIBLE_REDUCE_CONNECTIONS);
        }else{
            createPoolConnections(numberOfConnections - unavailablePoolConnections.size());
        }
    }
    
    public static void changeNumberOfPoolConnections(int newNumberOfConnections){
        numberOfConnections = newNumberOfConnections;
    }       
}
