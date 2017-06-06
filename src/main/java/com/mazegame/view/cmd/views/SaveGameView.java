package com.mazegame.view.cmd.views;

import static com.mazegame.view.cmd.views.MessageBundle.GAME_SAVED;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_ENTER_SAVE_ID;
import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_SAVE_GAME;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

import com.mazegame.core.model.game.Game;
import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.cmd.views.input.IInputHandler;
import com.mazegame.view.cmd.views.input.TextInputHandler;

/**
 * View to create a new hero.
 * 
 * @author Cesar
 *
 */
public class SaveGameView implements ICommandLineView {

  public ICommandLineView show(Game game) {
    PrintMessage.print(getMessage(TITTLE_SAVE_GAME));
    PrintMessage.print("");
    saveGame(game);

    return RoomView.newInstance();
  }

  private void saveGame(Game game) {
    String saveId = askId();
    game.saveGame(saveId);
    PrintMessage.print(getMessage(GAME_SAVED));
    PrintMessage.pressEnterToContinue();
  }

  private String askId() {
    IInputHandler<String> ti = new TextInputHandler(getMessage(MENU_ENTER_SAVE_ID));
    ti.show();
    return ti.getInput();
  }

  public static final SaveGameView newInstance() {
    return new SaveGameView();
  }

}
