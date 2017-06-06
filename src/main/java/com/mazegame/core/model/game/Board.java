package com.mazegame.core.model.game;

/**
 * A grid of rooms where the main player may find enemies. It contains both an
 * entry point and an exit room.
 * 
 * @author Cesar
 *
 */
public class Board {

  /** The grid of rooms. */
  private final Room[][] rooms;

  public Board(int rows, int columns) {
    rooms = new Room[rows][columns];
  }

  /**
   * Adds a new room at the specified position in the grid.
   * 
   * @param x row
   * @param y column
   * @param room the room to add
   */
  public void addRoom(int x, int y, Room room) {
    rooms[x][y] = room;
  }

  /**
   * Returns the room at the specified position in the grid.
   * 
   * @param x row
   * @param y column
   * @return the room at the specified position
   */
  public Room getRoom(int x, int y) {
    return rooms[x][y];
  }

  /**
   * Return the number of rows.
   * 
   * @return the number of rows.
   */
  public int getRowsCount() {
    return rooms.length;
  }

  /**
   * Return the number of columns.
   * 
   * @return the number of columns.
   */
  public int getColumsCount() {
    return rooms[0].length;
  }

  public Room[][] getRooms() {
    return rooms;
  }

}
