package com.example.carcassonne.data.game;

import com.example.carcassonne.domain.game.model.tile.Tile;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Tile,Integer> {

}
