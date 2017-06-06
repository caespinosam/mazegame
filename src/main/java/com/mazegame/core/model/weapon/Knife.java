package com.mazegame.core.model.weapon;

import com.mazegame.core.model.character.IAttackable;

/**
 * A basic weapon.
 * 
 * @author Cesar
 *
 */
public class Knife extends Item implements IWeapon {

    private static final  int DAMAGE_POINTS = 2;

    private Knife(String name) {
        super(name);
    }

    public static final Knife newInstance(String name) {
        return new Knife(name);
    }

    @Override
    public void attack(IAttackable attackableEntity) {
        // random stuff
        attackableEntity.receiveDamage(DAMAGE_POINTS);
    }

    @Override
    public IWeapon copy() {
        Knife clone = new Knife(this.name);
        return clone;
    }

    @Override
    public String toString() {
        return "Knife [name=" + name + "]";
    }

}
