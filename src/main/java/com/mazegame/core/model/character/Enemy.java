package com.mazegame.core.model.character;

/**
 * An enemy that lives in a room. It assumes a role to attack the hero/player.
 * The role can be assigned dynamically.
 * 
 * @author Cesar
 *
 */
public class Enemy extends BaseCharacter {

  /** The role or attack algorithm. */
  private IEnemyAttackBehavior attackBehavior;

  private Enemy(String name, int health, IEnemyAttackBehavior attackBehavior) {
    super(name, health);
    this.attackBehavior = attackBehavior;
  }

  /**
   * Factory method to create instances.
   * 
   * @param name
   * @param health the initial HP
   * @param attackBehavior the attack algorithm
   * @return a new instance
   */
  public static Enemy newInstance(String name, int health, IEnemyAttackBehavior attackBehavior) {
    return new Enemy(name, health, attackBehavior);
  }

  /**
   * Executes the attack algorithm to hit the player.
   * 
   * @param hero the player to hit
   */
  public void attackHero(Hero hero) {
    attackBehavior.attack(hero);
  }

  public String getAttackName() {
    return attackBehavior.getAttackName();
  }

}
