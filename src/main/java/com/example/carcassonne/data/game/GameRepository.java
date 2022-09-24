package com.example.carcassonne.data.game;

import com.example.carcassonne.domain.model.tile.Tile;
import com.example.carcassonne.domain.model.tile.TileRotation;
import com.example.carcassonne.domain.model.tile.TileType;
import org.apache.catalina.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepository extends CrudRepository<Tile,Integer> {
    @Query("update tiles set x = :x , y = :y , meeple = :meeple, meeple_player = :meeple_player, rotation = :rotation, type = :type , room_id = 1")
    @Modifying
    void setTile(int x, int y, String meeple, int meeple_player, TileRotation rotation, TileType type);
}
