package com.mazegame.core.model.weapon;

import java.util.UUID;

/**
 * A tool that the main player can carry in its bag.
 * 
 * @author Cesar
 *
 */
public abstract class Item {

  public UUID id;
  public String name;

  public Item(String name) {
    super();
    this.id = UUID.randomUUID();
    this.name = name;
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
    Item other = (Item) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    }
    else if (!id.equals(other.id))
      return false;
    return true;
  }

}
