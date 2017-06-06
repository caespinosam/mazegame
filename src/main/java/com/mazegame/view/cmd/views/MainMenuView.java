package com.mazegame.view.cmd.views;

import com.mazegame.core.model.game.Game;
import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.cmd.views.input.IInputHandler;
import com.mazegame.view.cmd.views.input.MenuInputHandler;
import com.mazegame.view.menu.Menu;
import com.mazegame.view.menu.MenuOption;

/**
 * View to show the main menu.
 * @author Cesar 
 *
 */
public class MainMenuView implements ICommandLineView {

    public ICommandLineView show(Game currentGame) {
        return showHomeMenu(currentGame);
    }

    private ICommandLineView showHomeMenu(Game currentGame) {

        PrintMessage.print("############## MAIN MENU ##########################");
        Menu<ICommandLineView> homeMenu = new Menu<>("Please select an option:");

        MenuOption<ICommandLineView> optionCreateCh = new MenuOption<>("1", "Create a hero", CreateHeroView.newInstance());
        homeMenu.addOption(optionCreateCh);

        MenuOption<ICommandLineView> optionPlayNewG = new MenuOption<>("2", "Play new game", SelectHeroView.newInstance());
        homeMenu.addOption(optionPlayNewG);

        IInputHandler<MenuOption<ICommandLineView>> inputHandler = new MenuInputHandler<>(homeMenu);
        inputHandler.show();

        return inputHandler.getInput().getSelectedValue();

    }

    public static final MainMenuView newInstance() {
        return new MainMenuView();
    }

}
