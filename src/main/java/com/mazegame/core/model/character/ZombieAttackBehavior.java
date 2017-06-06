package com.mazegame.core.model.character;

import java.util.Random;

/**
 * An implementation of {@link IEnemyAttackBehavior}. It simulates a zombie attack.  
 * 
 * @author Cesar 
 *
 */
public class ZombieAttackBehavior implements IEnemyAttackBehavior {
    
    private static final int DAMAGE_UPPER_LIMIT = 5;
    private static final int DAMAGE_LOWER_LIMIT = 1;

    @Override
    public void attack(Hero hero) {        
        Random rand = new Random();
        int randomDamage = rand.nextInt(DAMAGE_UPPER_LIMIT) + DAMAGE_LOWER_LIMIT;
        hero.receiveDamage(randomDamage);        
    }
    
    @Override
    public String getAttackName() {
      return "Zombie attack. Random damage units from " + DAMAGE_LOWER_LIMIT + "-" + DAMAGE_UPPER_LIMIT;
    }

}
