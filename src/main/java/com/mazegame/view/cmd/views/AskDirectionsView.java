package com.mazegame.view.cmd.views;

import com.mazegame.core.exception.InvalidMovementException;
import com.mazegame.core.model.game.Game;
import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.cmd.printer.PrinterMap;
import com.mazegame.view.cmd.printer.PrinterMapSimpleTableStrategy;
import com.mazegame.view.cmd.views.input.IInputHandler;
import com.mazegame.view.cmd.views.input.MenuInputHandler;
import com.mazegame.view.menu.Menu;
import com.mazegame.view.menu.MenuOption;
import com.mazegame.view.menu.ValueCommand;
import static com.mazegame.view.cmd.views.MessageBundle.*;

/**
 * A view to ask for the next step.
 * @author Cesar 
 *
 */
public class AskDirectionsView implements ICommandLineView {

    @Override
    public ICommandLineView show(Game currentGame) {
        PrintMessage.print("");
                
        Menu<ValueCommand> directionMenu = new Menu<>(getMessage(MENU_WHERE_TO_GO));
        MenuOption<ValueCommand> optionNorth = new MenuOption<>("1", getMessage(MENU_GO_NORTH), () -> currentGame.goNorth());
        directionMenu.addOption(optionNorth);
        MenuOption<ValueCommand> optionEast = new MenuOption<>("2", getMessage(MENU_GO_EAST), () -> currentGame.goEast());
        directionMenu.addOption(optionEast);
        MenuOption<ValueCommand> optionSouth = new MenuOption<>("3",getMessage( MENU_GO_SOUTH), () -> currentGame.goSouth());
        directionMenu.addOption(optionSouth);
        MenuOption<ValueCommand> optionWest = new MenuOption<>("4", getMessage(MENU_GO_WEST), () -> currentGame.goWest());
        directionMenu.addOption(optionWest);
        MenuOption<ValueCommand> optionMap = new MenuOption<>("5", getMessage(MENU_VIEW_MAP),
                () -> PrinterMap.printMap(new PrinterMapSimpleTableStrategy(currentGame.getCurrentState())));
        directionMenu.addOption(optionMap);

        IInputHandler<MenuOption<ValueCommand>> inputHandler = new MenuInputHandler<>(directionMenu);
        inputHandler.show();

        ValueCommand selectedOption = inputHandler.getInput().getSelectedValue();
        try {
            selectedOption.executeCommand();
        } catch (InvalidMovementException ime) {
            PrintMessage.print(ime.getMessage() + ". "  + getMessage(ERROR_CHECK_MAP));
        }

        return RoomView.newInstance();
    }


    public static final AskDirectionsView newInstance() {
        return new AskDirectionsView();
    }
}
