package com.mazegame.core.services;

import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.model.weapon.IWeaponRechargeable;
import com.mazegame.core.model.weapon.Poison;;

/**
 * An implementation of {@link IFightManager}. Executes a basic fight algorithm.
 * 
 * @author Cesar
 *
 */
public class FightManagerDummy implements IFightManager {

    private final static FightManagerDummy INSTANCE = new FightManagerDummy();
    private final static int AMMO_REWARD_UNITS = 10;

    private FightManagerDummy() {

    }

    /**
     * A basic fight. It just attack the enemy with the player's weapon. Then, the enemy attacks the
     * main player using its attack behavior .
     * 
     * @see com.mazegame.core.services.IFightManager#fight(com.mazegame.core.model.character.Hero,
     * com.mazegame.core.model.character.Enemy)
     */
    @Override
    public void fight(Hero hero, Enemy enemy) {
      if (!hero.isDead() && !enemy.isDead()) {
        if (hero.getWeapon() != null) {
            hero.getWeapon().attack(enemy);
        }

        enemy.attackHero(hero);

        // it gives a reward
        if (enemy.isDead()) {
            if (hero.getWeapon() instanceof IWeaponRechargeable) {
                ((IWeaponRechargeable) hero.getWeapon()).rechargeAmmo(AMMO_REWARD_UNITS);
            } else {
                Poison poison = Poison.newInstance("My poison", AMMO_REWARD_UNITS);
                hero.addItemBag(poison);
            }

        }
      }
    }

    /**
     * Returns a singleton instance.
     * 
     * @return
     */
    public static final FightManagerDummy getInstance() {
        return INSTANCE;
    }

}
