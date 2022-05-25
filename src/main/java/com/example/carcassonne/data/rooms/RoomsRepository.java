package com.example.carcassonne.data.rooms;

import com.example.carcassonne.domain.model.Room;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomsRepository extends CrudRepository<Room, Long> {
    @Query("INSERT INTO rooms(id, max_size, ready, name) Values(:id, :maxSize, :isReady, :name)")
    @Modifying
    void createRoom(int id, int maxSize, int isReady, String name);
    @Query("update rooms set ready = 1 where id = :id")
    @Modifying
    void setRoomReady(int id);


    List<Room> findAllRoomsByName(String name);
    List<Room> findAllRoomsById(int id);
    int countById(int id);

    @Query("Select ready from rooms where id = :id")
    int isReady(int id);

}
