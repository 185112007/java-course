package edu.javacourse.studentorder.exception;

/**
 * @author gafur
 */
public class TransportException extends Exception{
    public TransportException() {
        super();
    }

    public TransportException(String message) {
        super(message);
    }

    public TransportException(String message, Throwable cause) {
        super(message, cause);
    }
}
