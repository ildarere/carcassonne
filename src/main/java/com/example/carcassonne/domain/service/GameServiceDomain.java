package com.example.carcassonne.domain.service;

import com.example.carcassonne.data.game.GameRepository;
import com.example.carcassonne.domain.model.tile.TileRotation;
import com.example.carcassonne.domain.model.tile.TileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceDomain implements GameService{

    @Autowired
    GameRepository gameRepository;
    @Override
    public void setTile(int x, int y, String meeple, int meeple_player, TileRotation rotation, TileType type) {
        gameRepository.setTile(x, y, meeple, meeple_player, rotation, type);
    }
}
