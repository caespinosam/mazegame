package com.mazegame.core.model.game;

import com.mazegame.core.exception.InvalidMovementException;
import com.mazegame.core.model.character.Hero;

/**
 * The current state of a game. It keeps track of the current player and its position in the game.
 * 
 * @author Cesar
 *
 */
public class State {

    /** The current player. */
    private Hero player;
    /** The current challenge or stage. */
    private Board board;
    /** The current position of the player. */
    private Room currentRoom;
    /** The last position of the player. */
    private Room previousRoom;
    /** Coordinates of the current position in the grid/matrix. */
    private int currentPositionRow = 0;
    private int currentPositionColumn = 0;

    /**
     * Moves the current player one room to the north.
     * 
     * @throws InvalidMovementException
     */
    public void goNorth() throws InvalidMovementException {
        if (currentPositionRow > 0 && board.getRoom(currentPositionRow - 1, currentPositionColumn) != null) {
            Room nextRoom = board.getRoom(currentPositionRow - 1, currentPositionColumn);
            currentPositionRow--;
            previousRoom = currentRoom;
            setCurrentRoom(nextRoom);
        }

        throw new InvalidMovementException("north.");
    }

    /**
     * Moves the current player one room to the west.
     * 
     * @throws InvalidMovementException
     */
    public void goWest() throws InvalidMovementException {
        if (currentPositionColumn > 0 && board.getRoom(currentPositionRow, currentPositionColumn - 1) != null) {
            Room nextRoom = board.getRoom(currentPositionRow, currentPositionColumn - 1);
            currentPositionColumn--;
            previousRoom = currentRoom;
            setCurrentRoom(nextRoom);
        }

        throw new InvalidMovementException("west.");
    }

    /**
     * Moves the current player one room to the south.
     * 
     * @throws InvalidMovementException
     */
    public void goSouth() throws InvalidMovementException {
        if (currentPositionRow < board.getRowsCount() - 1 && board.getRoom(currentPositionRow + 1, currentPositionColumn) != null) {
            Room nextRoom = board.getRoom(currentPositionRow + 1, currentPositionColumn);
            currentPositionRow++;
            previousRoom = currentRoom;
            setCurrentRoom(nextRoom);
        }

        throw new InvalidMovementException("south.");
    }

    /**
     * Moves the current player one room to the east.
     * 
     * @throws InvalidMovementException
     */
    public void goEast() throws InvalidMovementException {
        if (currentPositionColumn < board.getColumsCount() - 1 && board.getRoom(currentPositionRow, currentPositionColumn + 1) != null) {
            Room nextRoom = board.getRoom(currentPositionRow, currentPositionColumn + 1);
            currentPositionColumn++;
            previousRoom = currentRoom;
            setCurrentRoom(nextRoom);
        }

        throw new InvalidMovementException("east");
    }

    /**
     * Moves the player to the specified room.
     * 
     * @param room the new position
     */
    public void setCurrentRoom(Room room) {
        for (int i = 0; i < board.getRowsCount(); i++) {
            for (int j = 0; j < board.getColumsCount(); j++) {
                if (room.equals(board.getRoom(i, j))) {
                    currentPositionRow = i;
                    currentPositionColumn = j;
                    previousRoom = currentRoom;
                    currentRoom = board.getRoom(i, j);
                    currentRoom.setVisited(true);
                    return;
                }
            }
        }
    }
    
    public void setInitialRoom() {
        for (int i = 0; i < board.getRowsCount(); i++) {
            for (int j = 0; j < board.getColumsCount(); j++) {
                if (board.getRoom(i, j) != null && board.getRoom(i, j).isEntrance()) {
                    currentPositionRow = i;
                    currentPositionColumn = j;
                    previousRoom = currentRoom;
                    currentRoom = board.getRoom(i, j);
                    currentRoom.setVisited(true);
                    return;
                }
            }
        }
    }

    public Hero getPlayer() {
        return player;
    }

    public void setPlayer(Hero player) {
        this.player = player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
        setInitialRoom();
    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

}
