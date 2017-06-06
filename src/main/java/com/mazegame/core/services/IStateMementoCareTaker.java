package com.mazegame.core.services;

import java.util.Iterator;

/**
 * Defines a behavior to save games.
 * 
 * @author Cesar
 *
 */
public interface IStateMementoCareTaker {

  /**
   * Saves a game state.
   * 
   * @param state the state to save.
   */
  void addState(StateMemento state);
  
  /**
   * Returns the saved state with the specified id.
   *@param id
   *@return
   */
  StateMemento getState(String id);

  /**
   * Returns all the saved game states.
   * 
   * @return all the saved game states.
   */
  Iterator<StateMemento> getStates();

}
