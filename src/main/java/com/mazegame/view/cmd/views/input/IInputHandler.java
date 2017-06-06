package com.mazegame.view.cmd.views.input;

/**
 * An interface to ask and read a value entered by the user.
 * 
 * @author Cesar 
 *
 * @param <T> the type to return
 */
public interface IInputHandler<T> {

  /**
   * Shows the assigned gui component.
   */
    void show();
    
    /**
     * Returns the typed input.
     * @return the typed input.
     */
    T getInput(); 
}
