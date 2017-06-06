package com.mazegame.view.cmd.views;

import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.model.game.Game;
import com.mazegame.core.model.game.Room;
import com.mazegame.view.cmd.printer.PrintMessage;

/**
 * View to show the options when the player steps in a room.
 * @author Cesar 
 *
 */
public class RoomView implements ICommandLineView {

    public ICommandLineView show(Game game) {

        PrintMessage.print("################# ROOM #######################");
        printMainPlayerInfo(game.getPlayer());
        Room currentRoom = game.getCurrentState().getCurrentRoom();
        PrintMessage.print("Name: " + currentRoom.getName());
        PrintMessage.print("Description: " + currentRoom.getDescription());
        if (currentRoom.getEnemy() != null) {
            Enemy enemy = currentRoom.getEnemy();
            if (!enemy.isDead()) {
                return askAttack();
            } else {
                PrintMessage.print("You have already defeated the enemy of this room. Now the room is empy. ");
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
        PrintMessage.print("- Your name: " + player.getName());
        PrintMessage.print("- Your weapon: " + player.getWeapon());
        PrintMessage.print("- Your HP: " + player.getCurretHP());
    }

    public static final RoomView newInstance() {
        return new RoomView();
    }

}
