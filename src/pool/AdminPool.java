/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool;

import java.sql.Connection;
import java.util.ArrayList;
import pool.exceptions.ConnectionsInUseException;
import pool.exceptions.NotAvailableConnectionsException;

/**
 *
 * @author miguelangel
 */
public class AdminPool {  
    private static AdminPool adminPool = null;   
    private static ArrayList<PoolConnection> availablePoolConnections;
    private static ArrayList<PoolConnection> unavailablePoolConnections;
    //private ParserXML parser;
    
    private static int numberOfConnections;
    
    private final static String NOT_AVAILABLE_CONNECTIONS = "No Hay Conexiones Disponibles";
    private final static String IMPOSSIBLE_REDUCE_CONNECTIONS = "Imposible reducir "
            + "el número de conexiones. Hay conexiones en uso";
    
    private AdminPool(){
        this.availablePoolConnections = new ArrayList();
        this.unavailablePoolConnections = new ArrayList();
    }
    
    public static AdminPool getInstance(){
        if(adminPool == null){
            adminPool = new AdminPool();
        }        
        return adminPool;
    }
    
    public void initializePoolConnections(int numberOfConnections){
        createPoolConnections(numberOfConnections);
    }
    
    public PoolConnection getPoolConnection() throws NotAvailableConnectionsException{
        if(this.availablePoolConnections.size()>0){
            for (int i = 0; i < this.availablePoolConnections.size(); i++) {
                if(this.availablePoolConnections.get(i).isActive()){
                    this.unavailablePoolConnections.add(this.availablePoolConnections.get(i));
                    return this.availablePoolConnections.get(i);
                }
            }
        }else{
            throw new NotAvailableConnectionsException(NOT_AVAILABLE_CONNECTIONS);
        }
           
        return null;
    }
    
    public void closeConnection(PoolConnection pc){
        this.availablePoolConnections.remove(pc);        
        pc.close();
        this.availablePoolConnections.add(pc);                
        this.unavailablePoolConnections.remove(pc);
        
        updatePoolConnections();
    }
    
    public void updatePoolConnections(){
        for (int i = 0; i < this.availablePoolConnections.size(); i++) {
            if(!this.availablePoolConnections.get(i).isActive()){
                this.availablePoolConnections.get(i).activate();
            }
        }
    }
    
    private void createPoolConnections(int numberOfConnections) {
        for (int i = 0; i < numberOfConnections; i++) {
            Connection connection = null;// = AdminConexionesBD.getConnection();
            PoolConnection pc = new PoolConnection(connection);
            this.availablePoolConnections.add(pc);
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
