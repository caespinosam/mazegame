package com.mazegame.core.model.board;

import java.util.UUID;

import com.mazegame.core.model.character.Enemy;

/**
 * A room in the board where the main player can step in.
 * 
 * @author Cesar.
 *
 */
public class Room {

  /** Artificial id. */
  private UUID id;
  private String name;
  private String description;
  /** Whether the player was already here. */
  private boolean isVisited;
  /** Whether this room is an exit. */
  private boolean isExit;
  /** Whether this room is the entrance. */
  private boolean isEntrance;
  /** The room may contain an enemy. */
  private Enemy enemy;

  private Room(String name, String description) {
    super();
    this.name = name;
    this.description = description;
    this.id = UUID.randomUUID();
  }

  /**
   * Factory method to create instances.
   * 
   * @param name the name of the room
   * @param description a short description of the room
   * @return new instance
   */
  public static Room newIntance(String name, String description) {
    return new Room(name, description);
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public boolean isVisited() {
    return isVisited;
  }

  public void setVisited(boolean visited) {
    this.isVisited = visited;
  }

  public boolean isExit() {
    return isExit;
  }

  public void setExit(boolean isExit) {
    this.isExit = isExit;
  }

  public Enemy getEnemy() {
    return enemy;
  }

  public void setEnemy(Enemy enemy) {
    this.enemy = enemy;
  }

  public boolean isEntrance() {
    return isEntrance;
  }

  public void setEntrance(boolean isEntrance) {
    this.isEntrance = isEntrance;
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
    Room other = (Room) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    }
    else if (!id.equals(other.id))
      return false;
    return true;
  }

}
