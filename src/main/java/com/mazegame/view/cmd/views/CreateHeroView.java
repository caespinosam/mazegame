package com.mazegame.view.cmd.views;

import static com.mazegame.view.cmd.views.MessageBundle.MENU_BOW;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_CHOOSE_WEAPON;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_ENTER_NAME;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_KNIFE;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_POISON;
import static com.mazegame.view.cmd.views.MessageBundle.NO_HERO_CREATED;
import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_CREATE_CHARACTER;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

import com.mazegame.core.exception.PlayerAlreadyExistException;
import com.mazegame.core.game.Game;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.model.weapon.Bow;
import com.mazegame.core.model.weapon.IWeapon;
import com.mazegame.core.model.weapon.Knife;
import com.mazegame.core.model.weapon.Poison;
import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.cmd.views.input.IInputHandler;
import com.mazegame.view.cmd.views.input.MenuInputHandler;
import com.mazegame.view.cmd.views.input.TextInputHandler;
import com.mazegame.view.menu.Menu;
import com.mazegame.view.menu.MenuOption;

/**
 * View to create a new hero.
 * 
 * @author Cesar
 *
 */
public class CreateHeroView implements ICommandLineView {

  private static final int INITIAL_HP = 100;
  private static final int INITIAL_BOW_ARROWS = 10;
  private static final int INITIAL_POISON_LITTERS = 3;

  public ICommandLineView show(Game game) {
    PrintMessage.print(getMessage(TITTLE_CREATE_CHARACTER));
    PrintMessage.print("");
    saveNewHero(game);

    return MainMenuView.newInstance();
  }

  private void saveNewHero(Game game) {
    String name = askName();
    IWeapon weapon = askWeapon(game);
    Hero newHero = Hero.newInstance(name, INITIAL_HP);
    newHero.setWeapon(weapon);
    try {
      game.savePlayer(newHero);
      PrintMessage.print(getMessage(NO_HERO_CREATED) + newHero.getName());
    }
    catch (PlayerAlreadyExistException paee) {
      PrintMessage.print(paee.getMessage());
    }
    PrintMessage.pressEnterToContinue();

  }

  private String askName() {
    IInputHandler<String> ti = new TextInputHandler(getMessage(MENU_ENTER_NAME));
    ti.show();
    return ti.getInput();
  }

  private IWeapon askWeapon(Game currentGame) {

    Menu<IWeapon> weaponMenu = new Menu<>(getMessage(MENU_CHOOSE_WEAPON));
    MenuOption<IWeapon> opKnife = new MenuOption<>("1", getMessage(MENU_KNIFE),
        Knife.newInstance("My Knife"));
    weaponMenu.addOption(opKnife);
    MenuOption<IWeapon> opBow = new MenuOption<>("2", getMessage(MENU_BOW),
        Bow.newInstance("My bow", INITIAL_BOW_ARROWS));
    weaponMenu.addOption(opBow);
    MenuOption<IWeapon> opPoison = new MenuOption<>("3", getMessage(MENU_POISON),
        Poison.newInstance("My poison", INITIAL_POISON_LITTERS));
    weaponMenu.addOption(opPoison);

    IInputHandler<MenuOption<IWeapon>> inputHandler = new MenuInputHandler<>(weaponMenu);
    inputHandler.show();
    IWeapon selectedWeapon = inputHandler.getInput().getSelectedValue();

    return selectedWeapon;

  }

  public static final CreateHeroView newInstance() {
    return new CreateHeroView();
  }

}
