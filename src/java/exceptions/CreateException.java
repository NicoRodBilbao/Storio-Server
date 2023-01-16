package exceptions;

/**
 * This exception class handles all errors referring to the creating operations.
 * @author Nicolás Rodríguez
 */
public class CreateException extends Exception {

    /**
     * Creates a new instance of <code>CreateException</code> without detail message.
     */
    public CreateException() {
    }


    /**
     * Constructs an instance of <code>CreateException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CreateException(String msg) {
        super(msg);
    }
}
