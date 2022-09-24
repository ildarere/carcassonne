//package com.example.carcassonne.domain.service.game.control.state;
//
//
//import com.example.carcassonne.domain.model.grid.GridDirection;
//import com.example.carcassonne.domain.game.settings.GameSettings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * The specific state if no game is running.
// * @author Timur Saglam
// */
//@Component
//public class StateIdle extends AbstractGameState {
//    @Autowired
//    GameSettings gameSettings;
//    /**
//     * Constructor of the state.
//     * @param stateMachine is the state machine managing this state.
//     * @param settings are the game settings.
//     */
//
//    public StateIdle( ) {
//        super();
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#abortGame()
//     */
//    @Override
//    public void abortGame() {
////        GameMessage.showMessage("There is currently no game running.");
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#newRound()
//     */
//    @Override
//    public void newRound() {
//        startNewRound(gameSettings.getNumberOfPlayers());
//    }
//
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#placeMeeple()
//     */
//    @Override
//    public void placeMeeple(GridDirection position) {
//        throw new IllegalStateException("Placing meeples in StateIdle is not allowed.");
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#placeTile()
//     */
//    @Override
//    public void placeTile(int x, int y) {
//        // do nothing.
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#skip()
//     */
//    @Override
//    public void skip() {
//        throw new IllegalStateException("There is nothing to skip in StateIdle.");
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#entry()
//     */
//    @Override
//    protected void entry() {
////        views.onMainView(MainView::resetGrid);
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#exit()
//     */
//    @Override
//    protected void exit() {
//        // No exit functions.
//    }
//
//}
