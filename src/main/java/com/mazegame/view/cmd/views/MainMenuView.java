package com.mazegame.view.cmd.views;

import com.mazegame.core.model.game.Game;
import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.cmd.views.input.IInputHandler;
import com.mazegame.view.cmd.views.input.MenuInputHandler;
import com.mazegame.view.menu.Menu;
import com.mazegame.view.menu.MenuOption;
import static com.mazegame.view.cmd.views.MessageBundle.*;

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
      	
      	currentGame.initState();
        PrintMessage.print(getMessage(TITTLE_MAIN_MENU));
        Menu<ICommandLineView> homeMenu = new Menu<>(getMessage(MENU_SELECT_OPTION));

        MenuOption<ICommandLineView> optionCreateCh = new MenuOption<>("1", getMessage(MENU_CREATE_HERO), CreateHeroView.newInstance());
        homeMenu.addOption(optionCreateCh);

        MenuOption<ICommandLineView> optionPlayNewG = new MenuOption<>("2", getMessage(MENU_PLAY_GAME), SelectHeroView.newInstance());
        homeMenu.addOption(optionPlayNewG);
        
        MenuOption<ICommandLineView> optionExit = new MenuOption<>("3", getMessage(MENU_EXIT), null);
        homeMenu.addOption(optionExit);

        IInputHandler<MenuOption<ICommandLineView>> inputHandler = new MenuInputHandler<>(homeMenu);
        inputHandler.show();

        return inputHandler.getInput().getSelectedValue();

    }

    public static final MainMenuView newInstance() {
        return new MainMenuView();
    }

}
