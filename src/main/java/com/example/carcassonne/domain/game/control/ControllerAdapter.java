package com.example.carcassonne.domain.game.control;

import com.example.carcassonne.domain.game.model.grid.GridDirection;
import com.example.carcassonne.domain.game.settings.GameSettings;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ControllerFacade adapter for view classes that manages the AWT/Swing threading for them and delegates all calls to a
 * real controller.
 */
public class ControllerAdapter implements ControllerFacade {

    private final MainController controller;
    private final ExecutorService service;

    /**
     * Creates the controller adapter from a original controller.
     * @param controller is the original to which all calls are delegated to.
     */
    ControllerAdapter(MainController controller) {
        this.controller = controller;
        service = Executors.newSingleThreadExecutor();
    }

    @Override
    public void requestAbortGame() {
       runInBackground(() -> controller.requestAbortGame());
    }

    @Override
    public void requestMeeplePlacement(GridDirection position) {
        runInBackground(() -> controller.requestMeeplePlacement(position));

    }

    @Override
    public void requestNewRound() {
        runInBackground(() -> controller.requestNewRound());
    }

    @Override
    public void requestSkip() {
        runInBackground(() -> controller.requestSkip());
    }

    @Override
    public void requestTilePlacement(int x, int y) {
        runInBackground(() -> controller.requestTilePlacement(x, y));
    }


    @Override
    public GameSettings getSettings() {
        return controller.getSettings(); // TODO (HIGH) [THREADING] Should this be on view thread?
    }

    private void runInBackground(Runnable task) {
        //service.submit(new ErrorReportingRunnable(task, "UI request led to an error:\n"));
    }

}
