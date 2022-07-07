package com.example.carcassonne.domain.game.control.state;


import com.example.carcassonne.domain.game.model.grid.GridDirection;
import com.example.carcassonne.domain.game.settings.GameSettings;

/**
 * The specific state if no game is running.
 * @author Timur Saglam
 */
public class StateIdle extends AbstractGameState {

    /**
     * Constructor of the state.
     * @param stateMachine is the state machine managing this state.
     * @param settings are the game settings.
     */
    public StateIdle(StateMachine stateMachine, GameSettings settings ) {
        super(stateMachine, settings);
    }

    /**
     * @see carcassonne.control.state.AbstractGameState#abortGame()
     */
    @Override
    public void abortGame() {
//        GameMessage.showMessage("There is currently no game running.");
    }

    /**
     * @see carcassonne.control.state.AbstractGameState#newRound()
     */
    @Override
    public void newRound(int playerCount) {
        startNewRound(playerCount);
    }

    /**
     * @see carcassonne.control.state.AbstractGameState#placeMeeple()
     */
    @Override
    public void placeMeeple(GridDirection position) {
        throw new IllegalStateException("Placing meeples in StateIdle is not allowed.");
    }

    /**
     * @see carcassonne.control.state.AbstractGameState#placeTile()
     */
    @Override
    public void placeTile(int x, int y) {
        // do nothing.
    }

    /**
     * @see carcassonne.control.state.AbstractGameState#skip()
     */
    @Override
    public void skip() {
        throw new IllegalStateException("There is nothing to skip in StateIdle.");
    }

    /**
     * @see carcassonne.control.state.AbstractGameState#entry()
     */
    @Override
    protected void entry() {
//        views.onMainView(MainView::resetGrid);
    }

    /**
     * @see carcassonne.control.state.AbstractGameState#exit()
     */
    @Override
    protected void exit() {
        // No exit functions.
    }

}
