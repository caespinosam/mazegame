package com.mazegame.core.model.character;

import java.util.UUID;

/**
 * Base class for the game characters. 
 * 
 * @author Cesar 
 *
 */
public abstract class BaseCharacter implements IAttackable {

    /** Artificial ID generated automatically. */
    public UUID id;
    /** Current HP.  */
    private int health;
    /** Natural name. */
    private String name;

    public BaseCharacter(String name, int health) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.health =  health;
    }

    @Override
    public int getCurretHP() {
        return health;
    }

    @Override
    public void receiveDamage(int damagePoints) {
        health = health - damagePoints;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public boolean isDead() {
        return !(health > 0);
    }

    public String getName() {
        return name;
    }
}
