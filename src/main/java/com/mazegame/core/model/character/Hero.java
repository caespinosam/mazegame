package com.mazegame.core.model.character;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.mazegame.core.model.weapon.IWeapon;
import com.mazegame.core.model.weapon.Item;

/**
 * Main player of the game.
 * 
 * @author Cesar
 *
 */
public class Hero extends BaseCharacter {

    /** The hero may have a weapon. */
    private IWeapon weapon;
    /** The hero carries a bag of items (other weapons, etc) */
    private Set<Item> itemsBag = new HashSet<>();

    private Hero(String name, int health) {
        super(name, health);

    }

    /**
     * Factory method to create instances.
     * 
     * @param name
     * @param health the initial HP
     * @return a new instance
     */
    public static Hero newInstance(String name, int health) {
        return new Hero(name, health);
    }

    /**
     * Sets a weapon.
     * 
     * @param weapon
     */
    public void setWeapon(IWeapon weapon) {
        this.weapon = weapon;
        if (!itemsBag.contains(weapon)) {
            itemsBag.add((Item) weapon);
        }
    }

    public IWeapon getWeapon() {
        return weapon;
    }

    public void addItemBag(Item item) {
        itemsBag.add(item);
    }

    /**
     * Returns those items that are weapons.
     * 
     * @return the weapons in the bag
     */
    public Iterator<IWeapon> getWeaponItems() {
        Set<IWeapon> weaponsInBag = new HashSet<>();
        Iterator<Item> iterator = itemsBag.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item instanceof IWeapon) {
                IWeapon wp = (IWeapon) item;
                if (!wp.equals(weapon)) {
                    weaponsInBag.add((IWeapon) item);
                }
            }
        }

        return weaponsInBag.iterator();
    }

    /**
     * Creates a copy of the instance.
     * 
     * @see java.lang.Object#clone()
     */
    public Hero clone() {
        Hero clone = new Hero(getName(), getCurretHP());
        if (this.weapon != null) {
            clone.setWeapon(this.weapon.copy());
        }

        Iterator<Item> iterator = itemsBag.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            clone.addItemBag(item);
        }

        return clone;
    }

}
