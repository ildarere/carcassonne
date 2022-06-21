package com.example.carcassonne.data.rooms;

import com.example.carcassonne.domain.model.Room;
import com.example.carcassonne.domain.model.UserData;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room,Integer> {

    @Query("INSERT INTO rooms(id, max_size, ready, name) Values(:id, :maxSize, :isReady, :name) " +
            "select LAST_INSERT_ID();")
    @Modifying
    int createRoom(int id, int maxSize, int isReady, String name);

    @Query("update rooms set ready = 1 where id = :id")
    @Modifying
    void setRoomReady(int id);

    @Query("select * from rooms")
    List<Room> getAllRooms();

    List<Room> findAllRoomsByName(String name);

    @Query("Select id, max_size, ready, name from rooms where id = :id ")
    Optional<Room> findRoomById(int id);

    int countById(int id);

    @Query("Select ready from rooms where id = :id")
    int isReady(int id);

    @Query("Select roomId, " +
            "user_information.id, user_information.rating, user_information.games_count, user_information.wins, user_information.name " +
            "From users_in_rooms " +
            "left join user_information on user_information.id = users_in_rooms.userid " +
            "where roomId = :id")
    List<UserData> getUsersFromRoom(int id);

    @Query("INSERT INTO users_in_rooms(userId, roomId) Values(:userId, :roomId) ")
    void addUserInRoom(int userId, int roomId);

    @Query("select Count(*) from rooms where name like :name")
    int countByName(String name);
    @Query("DELETE FROM  users_in_rooms " +
            "where userId = :userId ")
    void deleteUserFromRoom(int userId);


    @Query("Select Count(*) from users_in_rooms where roomId = :roomId" )
    int countUsersInRoom(int roomId);

}
