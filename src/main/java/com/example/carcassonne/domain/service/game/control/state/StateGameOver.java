//package com.example.carcassonne.domain.service.game.control.state;
//
//
//
//import com.example.carcassonne.domain.model.grid.GridDirection;
//import com.example.carcassonne.domain.model.grid.GridPattern;
//import com.example.carcassonne.domain.game.settings.GameSettings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * The specific state where the statistics are shown can be placed.
// * @author Timur Saglam
// */
//@Component
//public class StateGameOver extends AbstractGameState {
//@Autowired
//GameSettings gameSettings;
//    private static final String GAME_OVER_MESSAGE = "The game is over. Winning player(s): ";
//
//    /**
//     * Constructor of the state.
//     * @param stateMachine is the state machine managing this state.
//     * @param settings are the game settings.
//     */
//    public StateGameOver() {
//        super();
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#abortGame()
//     */
//    @Override
//    public void abortGame() {
//        // Do nothing, round is already aborted.
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#newRound()
//     */
//    @Override
//    public void newRound() {
//        exit();
//        changeState(StateIdle.class);
//        startNewRound(gameSettings.getNumberOfPlayers());
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#placeMeeple()
//     */
//    @Override
//    public void placeMeeple(GridDirection position) {
//        throw new IllegalStateException("Placing meeples in StateGameOver is not allowed.");
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
//       // views.onScoreboard(it -> it.disable());
//        exit();
//        changeState(StateIdle.class);
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#entry()
//     */
//    @Override
//    protected void entry() {
//        System.out.println("FINAL PATTERNS:"); // TODO (LOW) [PRINT] remove debug output
//        for (GridPattern pattern : grid.getAllPatterns()) {
//            System.out.println(pattern); // TODO (LOW) [PRINT] remove debug output
//            pattern.forceDisburse(settings.getSplitPatternScore());
//        }
//        updateScores();
//        updateStackSize();
//        //views.onMainView(it -> it.resetMenuState());
//        //GameMessage.showMessage(GAME_OVER_MESSAGE + round.winningPlayers());
//        //views.showGameStatistics(round);
//    }
//
//    /**
//     * @see carcassonne.control.state.AbstractGameState#exit()
//     */
//    @Override
//    protected void exit() {
//        //views.closeGameStatistics();
//    }
//}