package com.example.carcassonne.domain.service.game.control;


import com.example.carcassonne.domain.model.Meeple;
import com.example.carcassonne.domain.model.Player;
import com.example.carcassonne.domain.model.Round;
import com.example.carcassonne.domain.model.grid.Grid;
import com.example.carcassonne.domain.model.grid.GridDirection;
import com.example.carcassonne.domain.game.settings.GameSettings;
import com.example.carcassonne.domain.model.grid.GridPattern;
import com.example.carcassonne.domain.model.grid.GridSpot;
import com.example.carcassonne.domain.model.tile.TIleInf;
import com.example.carcassonne.domain.model.tile.Tile;
import com.example.carcassonne.domain.model.tile.TileStack;
import com.example.carcassonne.domain.service.game.control.state.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MainController implements ControllerFacade {


    @Autowired
    private GameState gameState;

    @Autowired
    private GameSettings settings;

    private boolean[] noMeeplesNotification;



    public GridSpot startGame() {
        noMeeplesNotification = new boolean[GameSettings.MAXIMAL_PLAYERS];
        return requestNewRound();

    }

    @Override
    public void requestAbortGame() {

    }

    @Override
    public GridSpot requestNewRound() {
        Grid newGrid = new Grid(settings.getGridWidth(), settings.getGridHeight(), settings.isAllowingEnclaves());
        TileStack tileStack = new TileStack(settings.getTileDistribution(), settings.getStackSizeMultiplier());
        Round newRound = new Round(settings.getNumberOfPlayers(), tileStack, newGrid, settings);
        gameState.updateState(newRound, tileStack, newGrid);
        GridSpot spot = newGrid.getFoundation();
        return spot;
    }

    @Override
    public Tile requestPlacingState() {
        Player player = gameState.getRound().getActivePlayer();
        if (!player.hasFullHand() && !gameState.getTileStack().isEmpty()) {
            Tile tile = gameState.getTileStack().drawTile();
            player.addTile(tile);
        }
        gameState.updateStackSize();
        if (gameState.getRound().isOver()) {
            requestGameOverState();
        } else  {
            return ((List<Tile>) player.getHandOfTiles()).get(0);
        }
        return null;
    }

    @Override
    public String requestManningState(Tile selectedTile) {
        Player player = gameState.getRound().getActivePlayer();
        String msg = null;
        if (player.hasFreeMeeples()) {
            noMeeplesNotification[player.getNumber()] = false; // resets out of meeple message!
            msg = "true";
        } else {
            if (!noMeeplesNotification[player.getNumber()]) { // Only warn player once until he regains meeples
                noMeeplesNotification[player.getNumber()] = true;
                msg ="You have no Meeples left. Regain Meeples by completing patterns to place Meepeles again.";
            }
//            processGridPatterns();
//            startNextTurn();
        }
        return msg;
    }

    @Override
    public void requestGameOverState() {

    }

    @Override
    public boolean requestMeeplePlacement(GridDirection position, Tile tile) {
        Player player = gameState.getRound().getActivePlayer();
        if (player.hasFreeMeeples() && tile.allowsPlacingMeeple(position, player, settings)) {
            tile.placeMeeple(player, position, settings);
            gameState.updateScores();
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method for the view to call if the user wants to skip a round.
     */
    @Override
    public void requestSkip() {

    }

    @Override
    public Tile requestTilePlacement(TIleInf tIleInf) {
        Tile tile = new Tile(tIleInf.getType());
        for (int i = 0; i < tIleInf.getNumOgLRotation(); i++) {
            tile.rotateLeft();
        }
        System.out.println(tIleInf.toString());

        if (gameState.getGrid().place(tIleInf.getX(), tIleInf.getY(),tile)) {
            gameState.getRound().getActivePlayer().dropTile(tile);
            GridSpot spot = gameState.getGrid().getSpot(tIleInf.getX(), tIleInf.getY());



            return spot.getTile();
        }
        return null;
        //  stateMachine.getCurrentState().placeTile(x, y);
    }


    private void processGridPatterns(Tile tile) {
        List<GridSpot> removeMeeples =new ArrayList<>();
        for (GridPattern pattern : gameState.getGrid().getModifiedPatterns(tile.getGridSpot())) {
            if (pattern.isComplete()) {
                for (Meeple meeple : pattern.getMeepleList()) {
                    GridSpot spot = meeple.getLocation();
                    removeMeeples.add(spot);
                }
                pattern.disburse(settings.getSplitPatternScore());
                gameState.updateScores();
            }
        }
    }
//private void startNextTurn() {
//    if (round.isOver()) {
//        changeState(StateGameOver.class);
//    } else {
//        if (!round.getActivePlayer().isComputerControlled()) {
//            views.onMainView(it -> it.resetPlacementHighlights());
//        }
//        round.nextTurn();
//        views.onMainView(it -> it.setCurrentPlayer(round.getActivePlayer()));
//        changeState(StatePlacing.class);
//    }
//}



    /**
     * Shows the main user interface.
     */
    @Override
    public List<GridSpot> highlightSurroundings(GridSpot spot) {
        List<GridSpot> highlight = new ArrayList<>();

        for (GridSpot neighbor : gameState.getGrid().getNeighbors(spot, true, GridDirection.directNeighbors())) {
            if (neighbor != null && neighbor.isFree()) {
                highlight.add(neighbor);
            }
        }
        return highlight;
    }

}
