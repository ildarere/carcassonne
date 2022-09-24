package com.example.carcassonne.domain.service.game.control.state;


import com.example.carcassonne.data.game.GameRepository;
import com.example.carcassonne.domain.model.Player;
import com.example.carcassonne.domain.model.Round;
import com.example.carcassonne.domain.model.grid.Grid;
import com.example.carcassonne.domain.model.grid.GridDirection;
import com.example.carcassonne.domain.model.grid.GridSpot;
import com.example.carcassonne.domain.model.tile.Tile;
import com.example.carcassonne.domain.model.tile.TileStack;
import com.example.carcassonne.domain.game.settings.GameSettings;
import com.example.carcassonne.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Is the   state of the state machine.
 * @author Timur Saglam
 */
@Component
public class GameState {


    @Autowired
    protected GameSettings settings;
    protected Round round;
    protected TileStack tileStack;
    protected Grid grid;

    /**
     * Constructor of the   state, sets the controller from the parameter, registers the state at the controller and
     * calls the <code>entry()</code> method.
     * @param stateMachine is the state machine managing this state.
     * @param settings are the game settings.

     */

    public GameState() {

    }


    /**
     * Starts new round with a specific amount of players.
     */
    public   void abortGame(){}


    public   void newRound(){}

    /**
     * Method for the view to call if a user mans a tile with a Meeple.
     * @param position is the placement position.
     */
    public   void placeMeeple(GridDirection position){}

    /**
     * Method for the view to call if a user places a tile.
     * @param x is the x coordinate.
     * @param y is the y coordinate.
     */
    public   void placeTile(int x, int y){}

    /**
     * Method for the view to call if the user wants to skip a round.
     */
    public   void skip(){}

    /**
     * Updates the round and the grid object after a new round was started.
     * @param round sets the new round.
     * @param tileStack sets the tile stack.
     * @param grid sets the new grid.
     */
    public void updateState(Round round, TileStack tileStack, Grid grid) {
        this.round = round;
        this.grid = grid;
        this.tileStack = tileStack;
    }

    /**
     * Changes the state of the state machine to a new state.
     * @param stateType is the class of the target state.
     */
//    protected void changeState(Class<? extends  GameState> stateType) {
//       // stateMachine.changeState(stateType); // Encapsulated in a method, as concrete state do not know the state machine
//    }

    /**
     * Entry method of the state.
     */
    protected   void entry(){}

    /**
     * Exit method of the state.
     */
    protected   void exit(){}

    /**
     * Starts a new round for a specific number of players.
     * @param playerCount is the specific number of players.
     */
    protected void startNewRound(int playerCount) {

    }

    /**
     * Updates the round and the grid of every state after a new round has been started.
     */
    public void updateScores() {
        for (int playerNumber = 0; playerNumber < round.getPlayerCount(); playerNumber++) {
            Player player = round.getPlayer(playerNumber);
           // views.onScoreboard(it -> it.update(player));
        }
    }

    /**
     * Returns the selected tile of the player. It does not matter if the player is a computer player or not.
     * @return the selected tile, either by a human player or the AI.
     */
    protected Tile getSelectedTile() {
        return null;
//        return views.getSelectedTile();
    }

    /**
     * Updates the label which displays the current stack size.
     */
    public void updateStackSize() {
//        views.onScoreboard(it -> it.updateStackSize(tileStack.getSize()));
    }

    /**
     * Highlights the surroundings of a {@link GridSpot} on the main view.
     * @param spot is the {@link GridSpot} that determines where to highlight.
     */
    protected void highlightSurroundings(GridSpot spot) {
        for (GridSpot neighbor : grid.getNeighbors(spot, true, GridDirection.directNeighbors())) {
            if (neighbor != null && neighbor.isFree()) {
//                views.onMainView(it -> it.setSelectionHighlight(neighbor.getX(), neighbor.getY()));
            }
        }
    }

    public Round getRound() {
        return round;
    }

    public TileStack getTileStack() {
        return tileStack;
    }

    public Grid getGrid() {
        return grid;
    }
}
