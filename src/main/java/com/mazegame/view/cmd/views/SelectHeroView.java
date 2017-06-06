package com.mazegame.view.cmd.views;

import static com.mazegame.view.cmd.views.MessageBundle.ERROR_NO_CHARACTERS;
import static com.mazegame.view.cmd.views.MessageBundle.MENU_SELECT_CHARACTER;
import static com.mazegame.view.cmd.views.MessageBundle.SELECTED_HERO;
import static com.mazegame.view.cmd.views.MessageBundle.TITTLE_SELECT_CHARACTER;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

import java.util.Iterator;

import com.mazegame.core.game.Game;
import com.mazegame.core.model.character.Hero;
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
public class SelectHeroView implements ICommandLineView {

  public ICommandLineView show(Game currentGame) {
    PrintMessage.print(getMessage(TITTLE_SELECT_CHARACTER));
    PrintMessage.print("");

    ICommandLineView nextView;
    if (isPlayersEmpty(currentGame)) {
      PrintMessage.print(getMessage(ERROR_NO_CHARACTERS));
      nextView = MainMenuView.newInstance();
    }
    else {
      Hero mainCharacter = askHero(currentGame);
      currentGame.setPlayer(mainCharacter);
      PrintMessage.print(getMessage(SELECTED_HERO) + mainCharacter.getName());
      nextView = RoomView.newInstance();
    }

    return nextView;
  }

  private Hero askHero(Game currentGame) {

    Hero selectedHero = null;
    Menu<Hero> characterMenu = new Menu<>(getMessage(MENU_SELECT_CHARACTER));

    int i = 1;
    Iterator<Hero> characters = currentGame.getCreatedPlayers();
    while (characters.hasNext()) {
      Hero character = characters.next();
      MenuOption<Hero> optCharacter = new MenuOption<>(String.valueOf(i), character.getName(),
          character);
      characterMenu.addOption(optCharacter);
      i++;
    }
    MenuInputHandler<Hero> mi = new MenuInputHandler<>(characterMenu);
    mi.show();
    selectedHero = mi.getInput().getSelectedValue();

    return selectedHero;

  }

  private boolean isPlayersEmpty(Game currentGame) {
    return !currentGame.getCreatedPlayers().hasNext();
  }

  public static final SelectHeroView newInstance() {
    return new SelectHeroView();
  }

}
