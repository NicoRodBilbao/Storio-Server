package exceptions;

/**
 * This exception class handles all errors referring to the creating operations.
 * @author Nicolás Rodríguez
 */
public class FindException extends Exception {

    /**
     * Creates a new instance of <code>FindException</code> without detail message.
     */
    public FindException() {
    }


    /**
     * Constructs an instance of <code>FindException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public FindException(String msg) {
        super(msg);
    }
}
