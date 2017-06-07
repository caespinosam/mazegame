package com.mazegame.core.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * An implementation that stores all the states in memory.
 * 
 * @author Cesar
 *
 */
public class StateMementoCareTakerInMemory implements IStateMementoCareTaker {

  private static final StateMementoCareTakerInMemory INSTANCE = new StateMementoCareTakerInMemory();

  /** All the saved game states. */
  private Map<String, StateMemento> mementos = new HashMap<>();

  private StateMementoCareTakerInMemory() {

  }

  /**
   * 
   * @see com.mazegame.core.services.IStateMementoCareTaker#addState(com.mazegame.core.services.StateMemento)
   */
  @Override
  public void addState(StateMemento state) {
    mementos.put(state.getId(), state);
  }

  /**
   * 
   * @see com.mazegame.core.services.IStateMementoCareTaker#getStates()
   */
  @Override
  public Iterator<StateMemento> getStates() {
    return mementos.values().iterator();
  }
  
  /**
   * 
   * @see com.mazegame.core.services.IStateMementoCareTaker#getState(java.lang.String)
   */
  @Override
  public StateMemento getState(String id) {
      return mementos.get(id);
  }

  /**
   * Returns a singleton instance.
   * 
   * @return a singleton instance
   */
  public static final StateMementoCareTakerInMemory getInstance() {
    return INSTANCE;
  }

 

}
