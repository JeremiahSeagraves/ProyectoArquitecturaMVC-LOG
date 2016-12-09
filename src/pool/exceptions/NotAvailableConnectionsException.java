/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pool.exceptions;

/**
 *
 * @author miguelangel
 */
public class NotAvailableConnectionsException extends Exception {
    
    public NotAvailableConnectionsException(String mensaje){
        super(mensaje);
    }
}
