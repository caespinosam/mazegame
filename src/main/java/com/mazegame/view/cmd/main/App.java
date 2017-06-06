package com.mazegame.view.cmd.main;

import com.mazegame.core.model.game.Game;
import com.mazegame.view.cmd.views.ICommandLineView;
import com.mazegame.view.cmd.views.MainMenuView;

/**
 * Launch the command-line app.
 * 
 * @author Cesar 
 *
 */
public class App {

    public void run() {
        mainLoop();
    }

    /**
     * Executes the game as long as a view is shown.
     */
    private void mainLoop() {

        Game game = Game.newInstance();
        MainMenuView menuView = MainMenuView.newInstance();
        ICommandLineView nextView = menuView.show(game);
        while (nextView != null) {
            nextView = nextView.show(game);
        }

    }

    public static void main(String... strings) {
        new App().run();
    }

}
