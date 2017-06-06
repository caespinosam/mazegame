package com.mazegame.core.services;

import com.mazegame.core.model.game.Board;

/**
 * Producer of boards.
 * @author Cesar 
 *
 */
public interface IBoardProducer {

    /**
     * Creates a board to use by the game.
     *@return
     */
    Board loadBoard();
}
