package com.mazegame.core.model.weapon;

import java.util.Random;

import com.mazegame.core.model.character.IAttackable;

/**
 * A rechargeable weapon.
 * 
 * @author Cesar
 *
 */
public class Poison extends Item implements IWeaponRechargeable {
    /** ammo */
    private int litters;

    private Poison(String name, int litters) {
        super(name);
        this.litters = litters;
    }

    public static final Poison newInstance(String name, int litters) {
        return new Poison(name, litters);
    }

    @Override
    public void attack(IAttackable attackableEntity) {
        // random stuff
        if (!isEmpty()) {
            Random rand = new Random();
            int randomDamage = rand.nextInt(20) + 1;
            attackableEntity.receiveDamage(randomDamage);
            litters--;
        }
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return !(litters > 0);
    }

    @Override
    public void rechargeAmmo(int units) {
        litters = litters + units;

    }

    @Override
    public IWeapon copy() {
        Poison clone = new Poison(this.name, this.litters);
        return clone;
    }

    @Override
    public String toString() {
        return "Poison [litters=" + litters + "]";
    }

}
