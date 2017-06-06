package com.mazegame.core.model.weapon;

import com.mazegame.core.model.character.IAttackable;

/**
 * The expected behavior from a weapon.
 * 
 * @author Cesar
 *
 */
public interface IWeapon {
    /**
     * Hits or shots the specified entity/character which is attackable.
     * 
     * @param attackableEntity the entity to hit.
     */
    void attack(IAttackable attackableEntity);

    /**
     * Return the name of the weapon.
     * 
     * @return name of the weapon.
     */
    String getName();

    /**
     * Creates a copy of the weapon.
     * 
     * @return a copy of the weapon.
     */
    IWeapon copy();
}
