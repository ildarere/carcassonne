//package com.example.carcassonne.domain.service.game.control.state;
//
//
//import com.example.carcassonne.domain.model.Meeple;
//import com.example.carcassonne.domain.model.Player;
//import com.example.carcassonne.domain.model.grid.GridDirection;
//import com.example.carcassonne.domain.model.grid.GridPattern;
//import com.example.carcassonne.domain.model.grid.GridSpot;
//import com.example.carcassonne.domain.model.tile.Tile;
//import com.example.carcassonne.domain.game.settings.GameSettings;
//import org.springframework.stereotype.Component;
//
///**
// * The specific state when a Meeple can be placed.
// * @author Timur Saglam
// */
//@Component
//public class StateManning extends AbstractGameState {
//    private boolean[] noMeeplesNotification;
//
//    /**
//     * Constructor of the state.
//     * @param stateMachine is the state machine managing this state.
//     * @param settings are the game settings.
//     */
//    public StateManning() {
//        super();
//        noMeeplesNotification = new boolean[GameSettings.MAXIMAL_PLAYERS]; // stores whether a player was already notified about a lack of meeples
//    }
//
//    /**
//     */
//    @Override
//    public void abortGame() {
//        changeState(StateGameOver.class);
//    }
//
//    /**
//     */
//    @Override
//    public void newRound() {
////        GameMessage.showWarning("Abort the current game before starting a new one.");
//    }
//
//    /**
//     */
//    @Override
//    public void placeMeeple(GridDirection position) {
//        if (!round.getActivePlayer().isComputerControlled()) {
//            Tile tile =null ;
////            Tile tile = views.getSelectedTile();
////            views.onMainView(it -> it.resetMeeplePreview(tile));
//            placeMeeple(position, tile);
//        }
//    }
//
//    /**
//     */
//    @Override
//    public void placeTile(int x, int y) {
//        // do nothing.
//    }
//
//    /**
//     */
//    @Override
//    public void skip() {
//        if (!round.getActivePlayer().isComputerControlled()) {
//            skipPlacingMeeple();
//        }
//    }
//
//    private void placeMeeple(GridDirection position, Tile tile) {
//        Player player = round.getActivePlayer();
//        if (player.hasFreeMeeples() && tile.allowsPlacingMeeple(position, player, settings)) {
//            tile.placeMeeple(player, position, settings);
////            views.onMainView(it -> it.setMeeple(tile, position, player));
//            updateScores();
//            processGridPatterns();
//            startNextTurn();
//        } else {
////            GameMessage.showWarning("You can't place meeple directly on an occupied Castle or Road!");
//        }
//    }
//
//    private void skipPlacingMeeple() {
//        if (!round.getActivePlayer().isComputerControlled()) {
////            Tile tile = views.getSelectedTile();
////            views.onMainView(it -> it.resetMeeplePreview(tile));
//        }
//        processGridPatterns();
//        startNextTurn();
//    }
//
//    // gives the players the points they earned.
//    private void processGridPatterns() {
//        Tile tile = getSelectedTile();
//        for (GridPattern pattern : grid.getModifiedPatterns(tile.getGridSpot())) {
//            if (pattern.isComplete()) {
//                for (Meeple meeple : pattern.getMeepleList()) {
//                    GridSpot spot = meeple.getLocation();
////                    views.onMainView(it -> it.removeMeeple(spot.getX(), spot.getY()));
//                }
//                pattern.disburse(settings.getSplitPatternScore());
//                updateScores();
//            }
//        }
//    }
//
//    // starts the next turn and changes the state to state placing.
//    private void startNextTurn() {
//        if (round.isOver()) {
//            changeState(StateGameOver.class);
//        } else {
//            if (!round.getActivePlayer().isComputerControlled()) {
////                views.onMainView(it -> it.resetPlacementHighlights());
//            }
//            round.nextTurn();
////            views.onMainView(it -> it.setCurrentPlayer(round.getActivePlayer()));
//            changeState(StatePlacing.class);
//        }
//    }
//
//
//
//    /**
//     */
//    @Override
//    protected void entry() {
//        Player player = round.getActivePlayer();
//        Tile selectedTile = getSelectedTile();
//        if (player.hasFreeMeeples()) {
//            noMeeplesNotification[player.getNumber()] = false; // resets out of meeple message!
////            views.onMainView(it -> it.setMeeplePreview(selectedTile, player));
////            views.onMeepleView(it -> it.setTile(selectedTile, player));
//
//        } else {
//            if (!noMeeplesNotification[player.getNumber()] && !player.isComputerControlled()) { // Only warn player once until he regains meeples
////                GameMessage.showMessage("You have no Meeples left. Regain Meeples by completing patterns to place Meepeles again.");
//                noMeeplesNotification[player.getNumber()] = true;
//            }
//            processGridPatterns();
//            startNextTurn();
//        }
//    }
//
//    /**
//     */
//    @Override
//    protected void exit() {
////        views.onMeepleView(it -> it.setVisible(false));
//    }
//}
