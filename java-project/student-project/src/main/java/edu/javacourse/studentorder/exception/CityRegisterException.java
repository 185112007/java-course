package edu.javacourse.studentorder.exception;

/**
 * @author gafur
 */
public class CityRegisterException extends Exception{
    public CityRegisterException() {
    }

    public CityRegisterException(String message) {
        super(message);
    }

    public CityRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}
