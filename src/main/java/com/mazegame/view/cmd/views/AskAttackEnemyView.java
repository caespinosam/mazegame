package com.mazegame.view.cmd.views;

import static com.mazegame.view.cmd.views.MessageBundle.ATTACK_RESULTS;
import static com.mazegame.view.cmd.views.MessageBundle.ENEMY_ATTACK;
import static com.mazegame.view.cmd.views.MessageBundle.ENEMY_DEFEATED;
import static com.mazegame.view.cmd.views.MessageBundle.ENEMY_HP;
import static com.mazegame.view.cmd.views.MessageBundle.ENEMY_NAME;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_ATTACK;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_CHANGE_WEAPON;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_SELECT_ACTION;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_STEP_BACK;
import static com.mazegame.view.cmd.views.MessageBundle.NO_WEAPONS_IN_BAG;
import static com.mazegame.view.cmd.views.MessageBundle.PLAYER_ATTACKED;
import static com.mazegame.view.cmd.views.MessageBundle.PLAYER_DIED;
import static com.mazegame.view.cmd.views.MessageBundle.PLAYER_HP;
import static com.mazegame.view.cmd.views.MessageBundle.PLAYER_WEAPON;
import static com.mazegame.view.cmd.views.MessageBundle.ROOM_WITH_ENEMY;
import static com.mazegame.view.cmd.views.MessageBundle.USE_WEAPON_FROM_BAG;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

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
 * 
 * @author Cesar
 *
 */
public class AskAttackEnemyView implements ICommandLineView {

  @Override
  public ICommandLineView show(Game currentGame) {
    Room currentRoom = currentGame.getCurrentState().getCurrentRoom();
    Enemy enemy = currentRoom.getEnemy();

    PrintMessage.print(getMessage(ROOM_WITH_ENEMY));
    PrintMessage.print(getMessage(ENEMY_NAME) + enemy.getName());
    PrintMessage.print(getMessage(ENEMY_ATTACK) + enemy.getAttackName());
    PrintMessage.print(getMessage(ENEMY_HP) + enemy.getCurretHP());
    PrintMessage.print("");

    boolean continueAttacking = true;
    while (continueAttacking) {
      Menu<ValueCommand> homeMenu = new Menu<>(getMessage(MENU_SELECT_ACTION));
      MenuOption<ValueCommand> optionCreateCh = new MenuOption<>("1", getMessage(MENU_ATTACK),
          () -> attack(currentGame));
      homeMenu.addOption(optionCreateCh);
      MenuOption<ValueCommand> optionPlayNewG = new MenuOption<>("2", getMessage(MENU_STEP_BACK),
          () -> currentGame.goPreviousRoom());
      homeMenu.addOption(optionPlayNewG);
      MenuOption<ValueCommand> optionChangeWeapon = new MenuOption<>("3",
          getMessage(MENU_CHANGE_WEAPON), () -> changeWeapon(currentGame));
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

      if (currentGame.isGameOver()) {
        PrintMessage.print(getMessage(PLAYER_DIED));
        PrintMessage.pressEnterToContinue();
        return MainMenuView.newInstance();
      }
      else if (enemy.isDead()) {
        PrintMessage.print(getMessage(ENEMY_DEFEATED));
        PrintMessage.pressEnterToContinue();
        return RoomView.newInstance();
      }

    }

    return RoomView.newInstance();
  }

  /**
   * Execute the attack and shows the result.
   * 
   * @param game the current game
   */
  private void attack(Game game) {

    Room room = game.getCurrentState().getCurrentRoom();
    Enemy enemy = room.getEnemy();
    Hero player = game.getPlayer();

    final int hpBefore = player.getCurretHP();
    game.fight(player, enemy);

    PrintMessage.print("");
    PrintMessage.print(getMessage(ATTACK_RESULTS));
    if (hpBefore != player.getCurretHP()) {
      PrintMessage.print(getMessage(PLAYER_ATTACKED));
    }
    PrintMessage.print(getMessage(PLAYER_HP) + player.getCurretHP());
    PrintMessage.print(getMessage(PLAYER_WEAPON) + player.getWeapon());
    PrintMessage.print(getMessage(ENEMY_HP) + enemy.getCurretHP());
    PrintMessage.print("");
    PrintMessage.pressEnterToContinue();
  }

  /**
   * Shows an option to change the player's weapon.
   * 
   * @param game the current game
   */
  private void changeWeapon(Game game) {

    Hero player = game.getPlayer();
    if (player.getWeaponItems().hasNext()) {
      IWeapon selectedWeapon = null;
      Menu<IWeapon> weaponsMenu = new Menu<>(getMessage(USE_WEAPON_FROM_BAG));
      Iterator<IWeapon> weaponsBag = player.getWeaponItems();
      int i = 1;
      while (weaponsBag.hasNext()) {
        IWeapon weapon = weaponsBag.next();
        MenuOption<IWeapon> optCharacter = new MenuOption<>(String.valueOf(i), weapon.toString(),
            weapon);
        weaponsMenu.addOption(optCharacter);
        i++;
      }
      MenuInputHandler<IWeapon> inputHandler = new MenuInputHandler<>(weaponsMenu);
      inputHandler.show();
      selectedWeapon = inputHandler.getInput().getSelectedValue();
      player.setWeapon(selectedWeapon);

    }
    else {
      PrintMessage.print(getMessage(NO_WEAPONS_IN_BAG));
    }

  }

  public static final AskAttackEnemyView newInstance() {
    return new AskAttackEnemyView();
  }

}
