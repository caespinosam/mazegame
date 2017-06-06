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
  /** Current HP. */
  private int health;
  /** Natural name. */
  private String name;

  public BaseCharacter(String name, int health) {
    super();
    this.id = UUID.randomUUID();
    this.name = name;
    this.health = health;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseCharacter other = (BaseCharacter) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    }
    else if (!id.equals(other.id))
      return false;
    return true;
  }

}
