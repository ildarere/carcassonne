package com.example.carcassonne.data.rooms;

import com.example.carcassonne.domain.model.Room;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomsRepository extends CrudRepository<Room, Long> {
    @Query("INSERT INTO rooms(roomId, max_size, ready, name) Values(:roomId, :maxSize, :isReady, :name)")
    @Modifying
    void createRoom(int roomId, int maxSize, int isReady, String name);
    @Query("update rooms set ready = 1 where roomId = :roomId")
    @Modifying
    void setRoomReady(int roomId);

    List<Room> findAllRoomsByName(String name);

}
