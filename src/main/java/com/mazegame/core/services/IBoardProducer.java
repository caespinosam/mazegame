package com.mazegame.core.services;

import com.mazegame.core.model.board.Board;

/**
 * Producer of boards.
 * 
 * @author Cesar
 *
 */
public interface IBoardProducer {

  /**
   * Creates a board to use by the game.
   * 
   * @return a board
   */
  Board loadBoard();
}
