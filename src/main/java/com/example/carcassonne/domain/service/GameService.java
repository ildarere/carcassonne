package com.example.carcassonne.domain.service;

import com.example.carcassonne.domain.game.model.tile.Tile;

public interface GameService {
    void startGame(int id);
    Tile placeTile(Tile tile);
}
