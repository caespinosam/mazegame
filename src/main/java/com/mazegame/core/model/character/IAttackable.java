package com.mazegame.core.model.character;

/**
 * Define the behavior of a character involved in a fight and that may receive
 * some damage.
 * 
 * @author Cesar
 *
 */
public interface IAttackable {

  /**
   * Returns the current health of the character.
   * 
   * @return the current health.
   */
  int getCurretHP();

  /**
   * Controls the damage received by the opponent.
   * 
   * @param damagePoints the damage to receive
   */
  void receiveDamage(int damagePoints);

  /**
   * Determines whether or not the character is dead.
   * 
   * @return true is dead, false otherwise
   */
  boolean isDead();

}
