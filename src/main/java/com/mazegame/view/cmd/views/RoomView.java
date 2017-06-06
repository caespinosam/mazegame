package com.mazegame.view.cmd.views;

import static com.mazegame.view.cmd.views.MessageBundle.ENEMY_DIED;
import static com.mazegame.view.cmd.views.MessageBundle.PLAYER_DIED;
import static com.mazegame.view.cmd.views.MessageBundle.PLAYER_HP;
import static com.mazegame.view.cmd.views.MessageBundle.PLAYER_NAME;
import static com.mazegame.view.cmd.views.MessageBundle.PLAYER_WEAPON;
import static com.mazegame.view.cmd.views.MessageBundle.ROOM_DESCRIPTION;
import static com.mazegame.view.cmd.views.MessageBundle.ROOM_NAME;
import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_ROOM;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

import com.mazegame.core.game.Game;
import com.mazegame.core.model.board.Room;
import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.Hero;
import com.mazegame.view.cmd.printer.PrintMessage;

/**
 * View to show the options when the player steps in a room.
 * 
 * @author Cesar
 *
 */
public class RoomView implements ICommandLineView {

  public ICommandLineView show(Game game) {

    PrintMessage.print(getMessage(TITTLE_ROOM));
    printMainPlayerInfo(game.getPlayer());
    Room currentRoom = game.getCurrentState().getCurrentRoom();
    PrintMessage.print(getMessage(ROOM_NAME) + currentRoom.getName());
    PrintMessage.print(getMessage(ROOM_DESCRIPTION) + currentRoom.getDescription());

    if (game.isGameOver()) {
      PrintMessage.print(getMessage(PLAYER_DIED));
      PrintMessage.pressEnterToContinue();
      return MainMenuView.newInstance();
    }
   
    if (currentRoom.getEnemy() != null) {
      Enemy enemy = currentRoom.getEnemy();
      if (!enemy.isDead()) {
        return askAttack();
      }
      else {
        PrintMessage.print(getMessage(ENEMY_DIED));
      }
    }
    
    if (game.isFinished()) {
      return ExitView.newInstance();
    }


    return askDirection();
  }

  private ICommandLineView askDirection() {
    return AskDirectionsView.newInstance();
  }

  private ICommandLineView askAttack() {
    return AskAttackEnemyView.newInstance();
  }

  private void printMainPlayerInfo(Hero player) {
    PrintMessage.print(getMessage(PLAYER_NAME) + player.getName());
    PrintMessage.print(getMessage(PLAYER_WEAPON) + player.getWeapon());
    PrintMessage.print(getMessage(PLAYER_HP) + player.getCurretHP());
  }

  public static final RoomView newInstance() {
    return new RoomView();
  }

}
