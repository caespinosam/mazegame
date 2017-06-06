package com.mazegame.view.cmd.views;

import static com.mazegame.view.cmd.views.MessageBundle.ERROR_NO_GAMES_SAVED;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_SELECT_GAME;
import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_RESUME_GAME;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

import java.util.Iterator;

import com.mazegame.core.model.game.Game;
import com.mazegame.core.model.game.State;
import com.mazegame.core.services.StateMemento;
import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.cmd.views.input.MenuInputHandler;
import com.mazegame.view.menu.Menu;
import com.mazegame.view.menu.MenuOption;

/**
 * View to select the player before starting the game.
 * 
 * @author Cesar
 *
 */
public class ResumeGameView implements ICommandLineView {

  public ICommandLineView show(Game currentGame) {
    PrintMessage.print(getMessage(TITTLE_RESUME_GAME));
    PrintMessage.print("");

    ICommandLineView nextView;
    if (isGamesSavedEmpty(currentGame)) {
      PrintMessage.print(getMessage(ERROR_NO_GAMES_SAVED));
      nextView = MainMenuView.newInstance();
    }
    else {
      State newState = askGame(currentGame);
      currentGame.setCurrentState(newState);
      nextView = RoomView.newInstance();
    }

    return nextView;
  }

  private State askGame(Game currentGame) {

    State selectedGame = null;
    Menu<State> gamesMenu = new Menu<>(getMessage(MENU_SELECT_GAME));

    int i = 1;
    Iterator<StateMemento> games = currentGame.getSavedGames();
    while (games.hasNext()) {
      StateMemento game = games.next();
      MenuOption<State> optGame = new MenuOption<>(String.valueOf(i), game.getId(),
          game.getSavedState());
      gamesMenu.addOption(optGame);
      i++;
    }
    MenuInputHandler<State> mi = new MenuInputHandler<>(gamesMenu);
    mi.show();
    selectedGame = mi.getInput().getSelectedValue();

    return selectedGame;

  }

  private boolean isGamesSavedEmpty(Game currentGame) {
    return !currentGame.getSavedGames().hasNext();
  }

  public static final ResumeGameView newInstance() {
    return new ResumeGameView();
  }

}
