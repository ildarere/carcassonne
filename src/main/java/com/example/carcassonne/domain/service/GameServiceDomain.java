package com.example.carcassonne.domain.service;

import com.example.carcassonne.data.game.GameRepository;
import com.example.carcassonne.domain.game.control.MainController;
import com.example.carcassonne.domain.game.model.tile.Tile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceDomain implements GameService {
    @Autowired
    GameRepository gameRepository;

    MainController mainController;
    @Override
    public void startGame(int id) {
        mainController = new MainController();
        mainController.requestNewRound();
    }

    @Override
    public Tile placeTile(Tile tile) {

        return tile;
    }
}
