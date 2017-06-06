package com.mazegame.view.cmd.views;

import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_EXIT_FOUND;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

import com.mazegame.core.model.game.Game;
import com.mazegame.view.cmd.printer.PrintMessage;

/**
 * View to show the end of the game.
 * 
 * @author Cesar
 *
 */
public class ExitView implements ICommandLineView {

  public ICommandLineView show(Game game) {

    PrintMessage.print(getMessage(TITTLE_EXIT_FOUND));
    PrintMessage.pressEnterToContinue();

    return MainMenuView.newInstance();
  }

  public static final ExitView newInstance() {
    return new ExitView();
  }

}
