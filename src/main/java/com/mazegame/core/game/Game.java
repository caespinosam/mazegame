package com.mazegame.core.game;

import java.util.Iterator;

import com.mazegame.core.model.board.Board;
import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.services.BoardProducerDummy;
import com.mazegame.core.services.FightManagerDummy;
import com.mazegame.core.services.IBoardProducer;
import com.mazegame.core.services.IFightManager;
import com.mazegame.core.services.IPlayerPersistor;
import com.mazegame.core.services.IStateMementoCareTaker;
import com.mazegame.core.services.PlayerPersistorInMemory;
import com.mazegame.core.services.StateMemento;
import com.mazegame.core.services.StateMementoCareTakerInMemory;

/**
 * Main facade. It provides the view layer with some core methods to control the
 * game.
 * 
 * @author Cesar
 *
 */
public class Game {

  private IPlayerPersistor playerPersistor = PlayerPersistorInMemory.getInstance();
  private IFightManager fightManager = FightManagerDummy.getInstance();
  private IBoardProducer boardProducer = BoardProducerDummy.getInstance();
  private IStateMementoCareTaker stateMementoCareTaker = StateMementoCareTakerInMemory
      .getInstance();

  /**
   * Keeps the current state of the game: player, current room, movements, etc .
   */
  private State currentState;

  private Game() {

  }

  /**
   * Allows to generate a new state for a new game.
   */
  public void initState() {
    init();
  }

  /**
   * Moves the current player one room to the north.
   */
  public void goNorth() {
    currentState.goNorth();
  }

  /**
   * Moves the current player one room to the west.
   */
  public void goWest() {
    currentState.goWest();
  }

  /**
   * Moves the current player one room to the south.
   */
  public void goSouth() {
    currentState.goSouth();
  }

  /**
   * Moves the current player one room to the east.
   */
  public void goEast() {
    currentState.goEast();
  }

  /**
   * Moves the current player to the previous room.
   */
  public void goPreviousRoom() {
    currentState.setCurrentRoom(currentState.getPreviousRoom());
  }

  /**
   * Whether the player found the exit.
   * 
   * @return
   */
  public boolean isFinished() {
    return currentState.getGameState() == EGameState.FINISHED;
  }

  /**
   * Whether the player died.
   * 
   * @return true if the player died
   */
  public boolean isGameOver() {
    return currentState.getGameState() == EGameState.GAME_OVER;
  }

  /**
   * Creates a new hero.
   * 
   * @param h the hero to create.
   */
  public void savePlayer(Hero h) {
    playerPersistor.savePlayer(h);
  }

  /**
   * Simulates a fight between the current player and an enemy.
   *
   * @param enemy the enemy.
   */
  public void fight(Enemy enemy) {
    fightManager.fight(currentState.getPlayer(), enemy);
    if (currentState.getPlayer().isDead()) {
      currentState.setGameState(EGameState.GAME_OVER);
    }
  }

  /**
   * Returns the current state of the game.
   * 
   * @return the current state of the game.
   */
  public State getCurrentState() {
    return currentState;
  }

  /**
   * Returns the current player.
   * 
   * @return the current player.
   */
  public Hero getPlayer() {
    return currentState.getPlayer();
  }

  /**
   * Sets the main player.
   * 
   * @param mainCharacter the main player of the current game.
   */
  public void setPlayer(Hero mainCharacter) {
    currentState.setPlayer(mainCharacter);
  }

  /**
   * Saves the current game state.
   * 
   * @param id the assigned id.
   */
  public void saveCurrentGame(String id) {
    stateMementoCareTaker.addState(new StateMemento(id, currentState));
  }

  /** 
   * Restores the specified game state.
   * 
   * @param oldGameId the game state to restore.
   */
  public void restoreState(String oldGameId) {
    StateMemento memento = stateMementoCareTaker.getState(oldGameId);
    currentState = memento.getSavedState();
  }

  /**
   * Returns all the saved game states.
   * 
   * @return all the saved game states.
   */
  public Iterator<StateMemento> getSavedGames() {
    return stateMementoCareTaker.getStates();
  }

  /**
   * Returns all the created players.
   * 
   * @return all the created players.
   */
  public Iterator<Hero> getCreatedPlayers() {
    return playerPersistor.getPlayers();
  }
  
  
  /**
   * Sets a new board producer. 
   * @param boardProducer a new board producer.
   */
  public void setBoardProducer(IBoardProducer boardProducer)
  {
    this.boardProducer = boardProducer;
  }
  
  
  /**
   * Sets a new fight algorithm. 
   * @param boardProducer  a new fight algorithm. 
   */
  public void setFightManager(IFightManager fightManager)
  {
    this.fightManager = fightManager;
  }

  /**
   * Sets a board and starts the state.
   */
  private void init() {
    Board board = boardProducer.loadBoard();
    currentState = new State();
    currentState.setBoard(board);
    currentState.setGameState(EGameState.RUNNING);
  }

  /**
   * Returns a new instance.
   * 
   * @return a new instance
   */
  public static final Game newInstance() {
    Game game = new Game();
    return game;
  }

}
