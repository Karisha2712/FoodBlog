package edu.radyuk.foodblog.exception;

/**
 * The type Data base connection exception.
 */
public class DataBaseConnectionException extends Exception {
    /**
     * Instantiates a new Data base connection exception.
     */
    public DataBaseConnectionException() {
    }

    /**
     * Instantiates a new Data base connection exception.
     *
     * @param message the message
     */
    public DataBaseConnectionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Data base connection exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DataBaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Data base connection exception.
     *
     * @param cause the cause
     */
    public DataBaseConnectionException(Throwable cause) {
        super(cause);
    }
}