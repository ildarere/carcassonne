//package com.example.carcassonne.domain.service.game.control.state;
//
//
//import com.example.carcassonne.data.game.GameRepository;
//import com.example.carcassonne.domain.model.Player;
//import com.example.carcassonne.domain.model.grid.GridDirection;
//import com.example.carcassonne.domain.model.grid.GridSpot;
//import com.example.carcassonne.domain.model.tile.Tile;
//import com.example.carcassonne.domain.game.settings.GameSettings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
///**
// * The specific state when a Tile can be placed.
// * @author Timur Saglam
// */
//@Component
//public class StatePlacing extends AbstractGameState {
//
//    @Autowired
//    private GameRepository gameRepository;
//    @Autowired
//    GameSettings settings;
//
//    private static final int SLEEP_DURATION = 10;
//
//    private Tile currentTile;
//    /**
//     * Constructor of the abstract state, sets the controller from the parameter, registers the state at the controller and
//     * calls the <code>entry()</code> method.
//     *
//       */
//
//    public StatePlacing(){
//        super();
//    }
//
//    /**
//     * Constructor of the state.
//     */
//
//
//
//    @Override
//    public void abortGame() {
//        changeState(StateGameOver.class);
//    }
//
//
//    @Override
//    public void newRound() {
////        GameMessage.showWarning("Abort the current game before starting a new one.");
//
//    }
//
//    @Override
//    public void placeMeeple(GridDirection position) {
//        throw new IllegalStateException("Placing meeples in StatePlacing is not allowed.");
//    }
//
//
//    @Override
//
//    public void placeTile(int x, int y) {
//
//            Tile tile = currentTile;
//            placeTile(tile, x, y, false);
//
//    }
//
//
//    @Override
//    public void skip() {
//        if (!round.getActivePlayer().isComputerControlled()) {
//            skipPlacingTile();
//        }
//    }
//
//    private void skipPlacingTile() {
//        Tile tile = getTileToDrop();
//        tileStack.putBack(tile);
//        if (!round.getActivePlayer().dropTile(tile)) {
//            throw new IllegalStateException("Cannot drop tile " + tile + "from player " + round.getActivePlayer());
//        }
//        if (!round.getActivePlayer().isComputerControlled()) {
////            views.onMainView(it -> it.resetPlacementHighlights());
//        }
//        round.nextTurn();
////        views.onMainView(it -> it.setCurrentPlayer(round.getActivePlayer()));
//        entry();
//    }
//
//    private void placeTile(Tile tile, int x, int y, boolean highlightPlacement) {
//        if (grid.place(x, y, tile)) {
//            round.getActivePlayer().dropTile(tile);
////            views.onMainView(it -> it.setTile(tile, x, y));
//            if (highlightPlacement) {
////                views.onMainView(view -> view.setPlacementHighlight(x, y));
//            }
//            GridSpot spot = grid.getSpot(x, y);
//            highlightSurroundings(spot);
//            changeState(StateManning.class);
//        }
//    }
//
//
//
//    private Tile getTileToDrop() {
////        views.getSelectedTile()
//        return null ;
//    }
//
//
//
//
//    @Override
//
//    protected void entry() {
//        Player player = round.getActivePlayer();
//        if (!player.hasFullHand() && !tileStack.isEmpty()) {
//            Tile tile = tileStack.drawTile();
//            currentTile = tile;
//            player.addTile(tile);
//
//            //gameRepository.setTile(0, 0,null,-1,null,tile.getType());
//        }
//        updateStackSize();
//        if (round.isOver()) {
//            changeState(StateGameOver.class);
//        } else  {
//
//        }
//    }
//
//
//    @Override
//    protected void exit() {
////        views.onTileView(it -> it.setVisible(false));
//    }
//
//}
