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
  public void add(StateMemento state);

  /**
   * Returns all the saved game states.
   * 
   * @return all the saved game states.
   */
  public Iterator<StateMemento> getStates();

}
