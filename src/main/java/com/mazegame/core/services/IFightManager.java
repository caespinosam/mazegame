package com.mazegame.core.services;

import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.Hero;

/**
 * Defines the behavior of a fight between the player and an enemy.
 * 
 * @author Cesar
 *
 */
public interface IFightManager {

  /**
   * Executes the algorithm that simulates a fight between the main player and
   * an enemy.
   * 
   * @param hero the main player
   * @param enemy an enemy
   */
  public void fight(Hero hero, Enemy enemy);
}
