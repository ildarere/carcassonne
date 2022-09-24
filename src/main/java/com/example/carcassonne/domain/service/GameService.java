package com.example.carcassonne.domain.service;

import com.example.carcassonne.domain.model.tile.TileRotation;
import com.example.carcassonne.domain.model.tile.TileType;

public interface GameService {

    void setTile(int x, int y, String meeple, int meeple_player, TileRotation rotation, TileType type);
}
