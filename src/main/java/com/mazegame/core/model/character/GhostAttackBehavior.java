package com.mazegame.core.model.character;

import java.util.Random;

/**
 * An implementation of {@link IEnemyAttackBehavior}. It simulates a ghost
 * attack.
 * 
 * @author Cesar
 *
 */
public class GhostAttackBehavior implements IEnemyAttackBehavior {

  private static final int DAMAGE_UPPER_LIMIT = 10;
  private static final int DAMAGE_LOWER_LIMIT = 5;

  /* (non-Javadoc)
   * 
   * @see
   * com.mazegame.core.model.character.IEnemyAttackBehavior#attack(com.mazegame.
   * core.model.character.Hero) */
  @Override
  public void attack(Hero hero) {
    Random rand = new Random();
    int randomDamage = rand.nextInt(DAMAGE_UPPER_LIMIT) + DAMAGE_LOWER_LIMIT;
    hero.receiveDamage(randomDamage);
  }

  /* (non-Javadoc)
   * 
   * @see com.mazegame.core.model.character.IEnemyAttackBehavior#getAttackName() */
  @Override
  public String getAttackName() {
    return "Ghost attack. Random damage units from " + DAMAGE_LOWER_LIMIT + DAMAGE_UPPER_LIMIT;
  }

}
