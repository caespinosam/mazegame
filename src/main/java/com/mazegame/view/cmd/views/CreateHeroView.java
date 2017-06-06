package com.mazegame.view.cmd.views;

import com.mazegame.core.exception.PlayerAlreadyExistException;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.model.game.Game;
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
 * @author Cesar 
 *
 */
public class CreateHeroView implements ICommandLineView {

    private static final int INITIAL_HP = 100;
    private static final int INITIAL_BOW_ARROWS = 10;
    private static final int INITIAL_POISON_LITTERS = 3;

    public ICommandLineView show(Game game) {
        PrintMessage.print("############## CREATE CHARACTER ##########################");
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
            PrintMessage.print("New hero created " + newHero.getName());
        } catch (PlayerAlreadyExistException paee) {
            PrintMessage.print(paee.getMessage());
        }

    }

    private String askName() {
        IInputHandler<String> ti = new TextInputHandler("Enter your name: ");
        ti.show();
        return ti.getInput();
    }

    private IWeapon askWeapon(Game currentGame) {

        Menu<IWeapon> weaponMenu = new Menu<>("Choose your favourite weapon: ");
        MenuOption<IWeapon> opKnife = new MenuOption<>("1", "Knife", Knife.newInstance("My Knife"));
        weaponMenu.addOption(opKnife);
        MenuOption<IWeapon> opBow = new MenuOption<>("2", "Bow", Bow.newInstance("My bow", INITIAL_BOW_ARROWS));
        weaponMenu.addOption(opBow);
        MenuOption<IWeapon> opPoison = new MenuOption<>("3", "Poison", Poison.newInstance("My poison", INITIAL_POISON_LITTERS));
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
