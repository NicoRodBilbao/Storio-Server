
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 * This exception class handles all errors referring to the removing operations.
 * @author Nicolás Rodríguez
 */
public class RemoveException extends Exception {

    /**
     * Creates a new instance of <code>RemoveException</code> without detail message.
     */
    public RemoveException() {
    }


    /**
     * Constructs an instance of <code>RemoveException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public RemoveException(String msg) {
        super(msg);
    }
}
