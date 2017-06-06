package com.mazegame.core.services;

import com.mazegame.core.game.State;

/**
 * A class to keep a game state that will be saved and restored.
 * 
 * @author Cesar
 *
 */
public class StateMemento {

  /** Identifies the saved game. */
  private String id;
  /** The game state to save. */
  private State gameState;

  public StateMemento(String id, State gameState) {
    this.id = id;
    this.gameState = gameState;
  }

  public State getSavedState() {
    return gameState;
  }

  public String getId() {
    return id;
  }

}
