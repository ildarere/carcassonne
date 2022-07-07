package com.example.carcassonne.domain.game.control.state;


import com.example.carcassonne.domain.game.model.Player;
import com.example.carcassonne.domain.game.model.Round;
import com.example.carcassonne.domain.game.model.grid.Grid;
import com.example.carcassonne.domain.game.model.grid.GridDirection;
import com.example.carcassonne.domain.game.model.grid.GridSpot;
import com.example.carcassonne.domain.game.model.tile.Tile;
import com.example.carcassonne.domain.game.model.tile.TileStack;
import com.example.carcassonne.domain.game.settings.GameSettings;
import com.example.carcassonne.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Is the abstract state of the state machine.
 * @author Timur Saglam
 */
public abstract class AbstractGameState { // TODO (HIGH) [AI] separate human move states from AI moves?

    private StateMachine stateMachine;
    protected GameSettings settings;
    @Autowired
    GameService gameService;
    protected Round round;
    protected TileStack tileStack;
    protected Grid grid;

    /**
     * Constructor of the abstract state, sets the controller from the parameter, registers the state at the controller and
     * calls the <code>entry()</code> method.
     * @param stateMachine is the state machine managing this state.
     * @param settings are the game settings.

     */
    public AbstractGameState(StateMachine stateMachine, GameSettings settings) {
        this.stateMachine = stateMachine;
        this.settings = settings;


    }

    /**
     * Starts new round with a specific amount of players.
     */
    public abstract void abortGame();

    /**
     * Starts new round with a specific amount of players.
     * @param playerCount sets the amount of players.
     */
    public abstract void newRound(int playerCount);

    /**
     * Method for the view to call if a user mans a tile with a Meeple.
     * @param position is the placement position.
     */
    public abstract void placeMeeple(GridDirection position);

    /**
     * Method for the view to call if a user places a tile.
     * @param x is the x coordinate.
     * @param y is the y coordinate.
     */
    public abstract void placeTile(int x, int y);

    /**
     * Method for the view to call if the user wants to skip a round.
     */
    public abstract void skip();

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
    protected void changeState(Class<? extends AbstractGameState> stateType) {
        stateMachine.changeState(stateType); // Encapsulated in a method, as concrete state do not know the state machine
    }

    /**
     * Entry method of the state.
     */
    protected abstract void entry();

    /**
     * Exit method of the state.
     */
    protected abstract void exit();

    /**
     * Starts a new round for a specific number of players.
     * @param playerCount is the specific number of players.
     */
    protected void startNewRound(int playerCount) {
        Grid newGrid = new Grid(settings.getGridWidth(), settings.getGridHeight(), settings.isAllowingEnclaves());
        TileStack tileStack = new TileStack(settings.getTileDistribution(), settings.getStackSizeMultiplier());
        Round newRound = new Round(playerCount, tileStack, newGrid, settings);
        stateMachine.updateStates(newRound, tileStack, newGrid);
        updateScores();
        updateStackSize();

        GridSpot spot = grid.getFoundation(); // starting spot.
        gameService.add
//        views.onMainView(it -> it.setTile(spot.getTile(), spot.getX(), spot.getY()));
        highlightSurroundings(spot);
//        for (int i = 0; i < round.getPlayerCount(); i++) {
//            Player player = round.getPlayer(i);
//        }
//        views.onMainView(it -> it.setCurrentPlayer(round.getActivePlayer()));
        changeState(StatePlacing.class);
    }

    /**
     * Updates the round and the grid of every state after a new round has been started.
     */
    protected void updateScores() {
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
    protected void updateStackSize() {
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
}
