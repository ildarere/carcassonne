package com.example.carcassonne.domain.service.game.control;


import com.example.carcassonne.domain.model.grid.GridDirection;
import com.example.carcassonne.domain.model.grid.GridSpot;
import com.example.carcassonne.domain.model.tile.TIleInf;
import com.example.carcassonne.domain.model.tile.Tile;

import java.util.List;


public interface ControllerFacade {
    GridSpot startGame();

    void requestAbortGame();



    GridSpot requestNewRound();

    Tile requestPlacingState();

    String requestManningState();

    void requestGameOverState();

    List<String> setMeeplePreview();

    GridDirection requestMeeplePlacement(GridDirection position);

    void requestSkip();

    Tile requestTilePlacement(TIleInf tIleInf);

    List<GridSpot> highlightSurroundings(GridSpot spot);

    void startNextTurn();

    List<GridSpot> processGridPatterns();
    List<Integer> getScores();









}