package com.mazegame.view.cmd.views;

import com.mazegame.core.game.Game;

/**
 * Behavior expected from the command line views.
 * 
 * @author Cesar
 *
 */
public interface ICommandLineView {

  /**
   * Shows the view.
   * 
   * @param currentGame the current game
   * @return the next view to show
   */
  ICommandLineView show(Game currentGame);

}
