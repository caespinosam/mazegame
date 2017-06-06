package com.mazegame.core.services;

import com.mazegame.core.model.board.Board;
import com.mazegame.core.model.board.Room;
import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.GhostAttackBehavior;
import com.mazegame.core.model.character.GoblinAttackBehavior;
import com.mazegame.core.model.character.ZombieAttackBehavior;

/**
 * Creates a simple board manually.
 * 
 * @author Cesar
 *
 */
public class BoardProducerDummy implements IBoardProducer {

  private static final BoardProducerDummy INSTANCE = new BoardProducerDummy();

  private BoardProducerDummy() {

  }

  /**
   * 
   * @see com.mazegame.core.services.IBoardProducer#loadBoard()
   */
  @Override
  public Board loadBoard() {

    Board gameBoard = new Board(5, 5);

    Room exitRoom = Room.newIntance("Exit", "Exit point");
    exitRoom.setExit(true);
    gameBoard.addRoom(0, 0, exitRoom);

    Enemy enemyGhost1 = Enemy.newInstance("Ghost 1", 40, new GhostAttackBehavior());
    Room ghost1Room = Room.newIntance("Death room", "A room with a ghost");
    ghost1Room.setEnemy(enemyGhost1);
    gameBoard.addRoom(0, 2, ghost1Room);

    Room empty1Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(0, 3, empty1Room);

    Room empty2Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(0, 4, empty2Room);

    Enemy enemyGhost2 = Enemy.newInstance("Ghost 2", 20, new GhostAttackBehavior());
    Room ghost2Room = Room.newIntance("Foggy room", "A room with a ghost");
    ghost2Room.setEnemy(enemyGhost2);
    gameBoard.addRoom(1, 0, ghost2Room);

    Room empty3Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(1, 4, empty3Room);

    Room empty4Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(2, 0, empty4Room);

    Enemy enemyZombie1 = Enemy.newInstance("Zombie 1", 15, new ZombieAttackBehavior());
    Room zombie1Room = Room.newIntance("Zombie room", "A room with a zombie");
    zombie1Room.setEnemy(enemyZombie1);
    gameBoard.addRoom(2, 1, zombie1Room);

    Room empty5Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(2, 2, empty5Room);

    Room empty6Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(2, 3, empty6Room);

    Enemy enemyZombie2 = Enemy.newInstance("Zombie 2", 30, new ZombieAttackBehavior());
    Room zombie2Room = Room.newIntance("Zombie room", "A room with a zombie");
    zombie2Room.setEnemy(enemyZombie2);
    gameBoard.addRoom(2, 4, zombie2Room);

    Room empty7Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(3, 0, empty7Room);

    Enemy enemyGoblin1 = Enemy.newInstance("Goblin 1", 20, new GoblinAttackBehavior());
    Room goblin1Room = Room.newIntance("Goblin room", "A room with a goblin");
    goblin1Room.setEnemy(enemyGoblin1);
    gameBoard.addRoom(3, 3, goblin1Room);

    Room empty8Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(3, 4, empty8Room);

    Enemy enemyGoblin2 = Enemy.newInstance("Goblin 2", 35, new GoblinAttackBehavior());
    Room goblin2Room = Room.newIntance("Goblin room", "A room with a goblin");
    goblin2Room.setEnemy(enemyGoblin2);
    gameBoard.addRoom(4, 1, goblin2Room);

    Room empty9Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(4, 2, empty9Room);

    Room empty10Room = Room.newIntance("Empty room", "Nothing here");
    gameBoard.addRoom(4, 3, empty10Room);

    Room empty11Room = Room.newIntance("Entry point", "This is the entrance");
    empty11Room.setEntrance(true);
    gameBoard.addRoom(4, 4, empty11Room);

    return gameBoard;
  }

  /**
   * Returns a singleton instance.
   * 
   * @return a singleton instance
   */
  public static final BoardProducerDummy getInstance() {
    return INSTANCE;
  }

}
