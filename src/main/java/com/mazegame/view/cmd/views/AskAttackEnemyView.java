package com.mazegame.view.cmd.views;

import java.util.Iterator;

import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.model.game.Game;
import com.mazegame.core.model.game.Room;
import com.mazegame.core.model.weapon.IWeapon;
import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.cmd.views.input.IInputHandler;
import com.mazegame.view.cmd.views.input.MenuInputHandler;
import com.mazegame.view.menu.Menu;
import com.mazegame.view.menu.MenuOption;
import com.mazegame.view.menu.ValueCommand;

/**
 * A view to control the fight between the player and an enemy.
 * @author Cesar 
 *
 */
public class AskAttackEnemyView implements ICommandLineView {

    @Override
    public ICommandLineView show(Game currentGame) {
        Room currentRoom = currentGame.getCurrentState().getCurrentRoom();
        Enemy enemy = currentRoom.getEnemy();
        Hero player = currentGame.getPlayer();

        PrintMessage.print("This is a room where an enemy lives. You must defeat your enemy in order to pass through this room. ");
        PrintMessage.print("- Enemy name: " + enemy.getName());
        PrintMessage.print("- Enemy attack: " + enemy.getAttackName());
        PrintMessage.print("- Enemy HP: " + enemy.getCurretHP());
        PrintMessage.print("");

        boolean continueAttacking = true;
        while (continueAttacking) {
            Menu<ValueCommand> homeMenu = new Menu<>("Please select an action!:");
            MenuOption<ValueCommand> optionCreateCh = new MenuOption<>("1", "Attack", () -> attack(currentGame));
            homeMenu.addOption(optionCreateCh);
            MenuOption<ValueCommand> optionPlayNewG = new MenuOption<>("2", "Step back", () -> currentGame.goPreviousRoom());
            homeMenu.addOption(optionPlayNewG);
            MenuOption<ValueCommand> optionChangeWeapon = new MenuOption<>("3", "Change weapon", () -> changeWeapon(currentGame));
            homeMenu.addOption(optionChangeWeapon);

            IInputHandler<MenuOption<ValueCommand>> inputHandler = new MenuInputHandler<>(homeMenu);
            inputHandler.show();

            MenuOption<ValueCommand> option = inputHandler.getInput();
            ValueCommand selectedOption = option.getSelectedValue();
            selectedOption.executeCommand();

            if (option.getId().equals(optionPlayNewG.getId())) {
                continueAttacking = false;
                return RoomView.newInstance();
            }

            if (player.isDead()) {
                PrintMessage.print("Unfortunately you have died. Game over.");
                return MainMenuView.newInstance();
            } else if (enemy.isDead()) {
                PrintMessage.print("-----> You have defeated your enemy!. A reward was given, either your ammo was increased or you received a new weapon in your bag.");
                // return manageRoom(currentGame, (Room)room);
                return RoomView.newInstance();
            }

        }

        return RoomView.newInstance();
    }

    /**
     * Execute the attack and shows the result.
     *@param game
     */
    private void attack(Game game) {

        Room room = game.getCurrentState().getCurrentRoom();
        Enemy enemy = room.getEnemy();
        Hero player = game.getPlayer();

        int hpBefore = player.getCurretHP();
        game.fight(player, enemy);

        PrintMessage.print("");
        PrintMessage.print("Attack Results:");
        if (hpBefore != player.getCurretHP()) {
            PrintMessage.print("**** you have been attacked!");
        }
        PrintMessage.print("**** your HP:" + player.getCurretHP());
        PrintMessage.print("**** your weapon:" + player.getWeapon());
        PrintMessage.print("**** enemy HP:" + enemy.getCurretHP());
        PrintMessage.print("");

    }

    /**
     * Shows an option to change the player's weapon.
     *@param game
     */
    private void changeWeapon(Game game) {

        Hero player = game.getPlayer();
        if (player.getWeaponItems().hasNext()) {
            IWeapon selectedWeapon = null;
            Menu<IWeapon> weaponsMenu = new Menu<>("Use a weapon from the bag");
            Iterator<IWeapon> weaponsBag = player.getWeaponItems();
            int i = 1;
            while (weaponsBag.hasNext()) {
                IWeapon weapon = weaponsBag.next();
                MenuOption<IWeapon> optCharacter = new MenuOption<>(String.valueOf(i), weapon.toString(), weapon);
                weaponsMenu.addOption(optCharacter);
                i++;
            }
            MenuInputHandler<IWeapon> inputHandler = new MenuInputHandler<>(weaponsMenu);
            inputHandler.show();
            selectedWeapon = inputHandler.getInput().getSelectedValue();
            player.setWeapon(selectedWeapon);

        } else {
            PrintMessage.print("No other weapons in your bag. ");
        }

    }

    public static final AskAttackEnemyView newInstance() {
        return new AskAttackEnemyView();
    }

}
