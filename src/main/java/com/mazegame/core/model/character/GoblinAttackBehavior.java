package com.mazegame.core.model.character;

/**
 * An implementation of {@link IEnemyAttackBehavior}. It simulates a goblin  attack.  
 * 
 * @author Cesar 
 *
 */
public class GoblinAttackBehavior implements IEnemyAttackBehavior {
    
    private static final int DAMAGE_UNITS = 2;
    

    @Override
    public void attack(Hero hero) {       
        hero.receiveDamage(DAMAGE_UNITS);        
    }
    
    @Override
    public String getAttackName() {
      return "Goblin attack. Damage units " + DAMAGE_UNITS;
    }

}
