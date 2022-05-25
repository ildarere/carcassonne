package com.example.carcassonne.data.rooms;

import com.example.carcassonne.domain.model.Room;
import com.example.carcassonne.domain.model.UserData;
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

    @Query("Select roomId," +
        " user_information.id, user_information.rating, user_information.games_count, user_information.wins, user_information.name " +
        "From users_in_rooms left join user_information on user_information.id = users_in_rooms.userid " +
        "where roomId = :id")
    List<UserData> getUsersFromRoom(int id);
}
