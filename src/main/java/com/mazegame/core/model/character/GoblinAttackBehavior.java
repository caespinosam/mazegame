package com.mazegame.core.model.character;

/**
 * An implementation of {@link IEnemyAttackBehavior}. It simulates a goblin  attack.  
 * 
 * @author Cesar 
 *
 */
public class GoblinAttackBehavior implements IEnemyAttackBehavior {
    
    private static final int DAMAGE_UNITS = 2;
    
    /*
     * (non-Javadoc)
     * @see com.mazegame.core.model.character.IEnemyAttackBehavior#attack(com.mazegame.core.model.character.Hero)
     */
    @Override
    public void attack(Hero hero) {       
        hero.receiveDamage(DAMAGE_UNITS);        
    }
    
    /*
     * (non-Javadoc)
     * @see com.mazegame.core.model.character.IEnemyAttackBehavior#getAttackName()
     */
    @Override
    public String getAttackName() {
      return "Goblin attack. Damage units " + DAMAGE_UNITS;
    }

}
