package com.mazegame.view.cmd.views;

import static com.mazegame.view.cmd.views.MessageBundle.MENU_CREATE_HERO;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_EXIT;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_PLAY_GAME;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_RESUME_GAME;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_SELECT_OPTION;
import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_MAIN_MENU;
import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_GAME;
import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_GAME_DESCRIPTION;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

import com.mazegame.core.game.Game;
import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.cmd.views.input.IInputHandler;
import com.mazegame.view.cmd.views.input.MenuInputHandler;
import com.mazegame.view.menu.Menu;
import com.mazegame.view.menu.MenuOption;

/**
 * View to show the main menu.
 * 
 * @author Cesar
 *
 */
public class MainMenuView implements ICommandLineView {

  public ICommandLineView show(Game currentGame) {
    return showHomeMenu(currentGame);
  }

  private ICommandLineView showHomeMenu(Game currentGame) {

    currentGame.initState();
    
    PrintMessage.print("");
    PrintMessage.print("");
    PrintMessage.print(getMessage(TITTLE_GAME));
    PrintMessage.print(getMessage(TITTLE_GAME_DESCRIPTION));
    PrintMessage.print("");
    
    PrintMessage.print(getMessage(TITTLE_MAIN_MENU));
    Menu<ICommandLineView> homeMenu = new Menu<>(getMessage(MENU_SELECT_OPTION));

    MenuOption<ICommandLineView> optionCreateCh = new MenuOption<>("1",
        getMessage(MENU_CREATE_HERO), CreateHeroView.newInstance());
    homeMenu.addOption(optionCreateCh);

    MenuOption<ICommandLineView> optionPlayNewG = new MenuOption<>("2", getMessage(MENU_PLAY_GAME),
        SelectHeroView.newInstance());
    homeMenu.addOption(optionPlayNewG);

    MenuOption<ICommandLineView> optionResume = new MenuOption<>("3", getMessage(MENU_RESUME_GAME),
        ResumeGameView.newInstance());
    homeMenu.addOption(optionResume);

    MenuOption<ICommandLineView> optionExit = new MenuOption<>("4", getMessage(MENU_EXIT), null);
    homeMenu.addOption(optionExit);

    IInputHandler<MenuOption<ICommandLineView>> inputHandler = new MenuInputHandler<>(homeMenu);
    inputHandler.show();

    return inputHandler.getInput().getSelectedValue();

  }

  public static final MainMenuView newInstance() {
    return new MainMenuView();
  }

}
