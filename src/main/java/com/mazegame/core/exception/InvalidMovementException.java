package com.mazegame.core.exception;

/**
 * Exception thrown when it is not possible to apply a movement.
 * @author Cesar 
 *
 */
public class InvalidMovementException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8494357378551630956L;

    public InvalidMovementException(String direction) {
        super("It is not possible to go " + direction);
    }

}
