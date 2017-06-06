package com.mazegame.core.exception;

/**
 * Exception thrown when a player with the same name already exists.
 * @author Cesar 
 *
 */
public class PlayerAlreadyExistException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8494357378551630956L;

    public PlayerAlreadyExistException(String name) {
        super("There is already a player with the name: " + name);
    }

}
