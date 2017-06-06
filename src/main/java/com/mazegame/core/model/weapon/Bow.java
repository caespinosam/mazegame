package com.mazegame.core.model.weapon;

import com.mazegame.core.model.character.IAttackable;

/**
 * A rechargeable weapon.
 * 
 * @author Cesar
 *
 */
public class Bow extends Item implements IWeaponRechargeable {

    /** Damage cause to the character. */
    private static final  int DAMAGE_POINTS = 10;
    /** Ammo. */
    private int arrows;

    private Bow(String name, int arrows) {
        super(name);
        this.arrows = arrows;
    }

    public static final Bow newInstance(String name, int arrows) {
        return new Bow(name, arrows);
    }

    @Override
    public void attack(IAttackable attackableEntity) {
        // random stuff
        if (arrows > 0) {
            attackableEntity.receiveDamage(DAMAGE_POINTS);
            arrows--;
        }
    }

    @Override
    public boolean isEmpty() {
        return !(arrows > 0);
    }

    @Override
    public void rechargeAmmo(int units) {
        arrows = arrows + units;

    }

    @Override
    public String toString() {
        return "Bow [arrows=" + arrows + ", name=" + name + "]";
    }

    @Override
    public IWeapon copy() {
        Bow clone = new Bow(this.name, this.arrows);
        return clone;
    }

}
