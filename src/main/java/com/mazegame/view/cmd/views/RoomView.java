package com.mazegame.view.cmd.views;

import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.model.game.Game;
import com.mazegame.core.model.game.Room;
import com.mazegame.view.cmd.printer.PrintMessage;
import static com.mazegame.view.cmd.views.MessageBundle.*;

/**
 * View to show the options when the player steps in a room.
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
        if (game.isFinished()) {          
          return ExitView.newInstance();
        } 
        
        if (currentRoom.getEnemy() != null) {
            Enemy enemy = currentRoom.getEnemy();
            if (!enemy.isDead()) {
                return askAttack();
            } else {
                PrintMessage.print(getMessage(ENEMY_DIED));
            }

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
