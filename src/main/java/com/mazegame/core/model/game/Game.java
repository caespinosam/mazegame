package com.mazegame.core.model.game;

import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.services.BoardProducerDummy;
import com.mazegame.core.services.FightManagerDummy;
import com.mazegame.core.services.IBoardProducer;
import com.mazegame.core.services.IFightManager;
import com.mazegame.core.services.IPlayerPersistor;
import com.mazegame.core.services.PlayerPersistorInMemory;

/**
 * Main facade. It provides the view layer with some core methods to control the game.
 * 
 * @author Cesar
 *
 */
public class Game {

    private final IPlayerPersistor playerPersistor = PlayerPersistorInMemory.getInstance();
    private final IFightManager fightManager = FightManagerDummy.getInstance();
    private final IBoardProducer boardProducer = BoardProducerDummy.getInstance();
    /** Keep the current state of the game: player, current room, movements, etc .*/
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
     * @return
     */
    public boolean isFinished() {
      return currentState.getGameState() == EGameState.FINISHED; 
    }
    
    /**
     * Whether the player died.
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
     * Simulates a fight between the player and an enemy.
     * 
     * @param hero the current player.
     * @param enemy the enemy.
     */
    public void fight(Hero hero, Enemy enemy) {
        fightManager.fight(hero, enemy);
        if (hero.isDead()) {
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

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
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
     * Returns a new instance.
     *@return
     */
    public static final Game newInstance() {
        Game game = new Game();       
        return game;
    }
    
    /**
     * Sets a board and starts the state.
     */
    private void init() {
      Board board =  boardProducer.loadBoard();
      currentState = new State();
      currentState.setBoard(board);     
      currentState.setGameState(EGameState.RUNNING);
  }
  

}
