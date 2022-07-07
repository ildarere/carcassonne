package com.example.carcassonne.domain.game.control;


import com.example.carcassonne.domain.game.model.grid.GridDirection;
import com.example.carcassonne.domain.game.settings.GameSettings;

/**
 * Facade for the game controller. Allows to call view classes or potentially external services to make requests regarding
 * the game flow.
 * @author Timur Saglam
 */
public interface ControllerFacade {

    /**
     * Requests to abort the round.
     */
    void requestAbortGame();

    /**
     * Requests to place a meeple on the current selected tile.
     * @param position is the position on the tile where the meeple is to be placed.
     */
    void requestMeeplePlacement(GridDirection position);

    /**
     * Requests to start a new round.
     */
    void requestNewRound();

    /**
     * Requests to skip either the current tile placement or the current meeple placement.
     */
    void requestSkip();

    /**
     * Requests to place a tile on the grid.
     * @param x is the x coordinate of the grid spot to place the tile.
     * @param y is the y coordinate of the grid spot to place the tile.
     */
    void requestTilePlacement(int x, int y);

    /**
     * Getter for the global key binding manager.
     * @return the global key bindings.
     */


    /**
     * Getter for the global game settings.
     * @return the settings.
     */
    GameSettings getSettings();

}