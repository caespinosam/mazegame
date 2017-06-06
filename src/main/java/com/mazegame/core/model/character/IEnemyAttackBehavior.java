package com.mazegame.core.model.character;

/**
 * Attack strategy assigned to an enemy.
 * 
 * @author Cesar
 *
 */
public interface IEnemyAttackBehavior {

    /**
     * Execute the algorithm to attack the main player.
     * 
     * @param hero the player to attack.
     */
    void attack(Hero hero);

    /**
     * A short description of the algorithm.
     * 
     * @return a short description of the algorithm.
     */
    String getAttackName();

}
