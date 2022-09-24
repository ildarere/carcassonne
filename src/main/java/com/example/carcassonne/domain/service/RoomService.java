package com.example.carcassonne.domain.service;

import com.example.carcassonne.domain.model.Room;
import com.example.carcassonne.domain.model.UserData;

import java.util.List;
import java.util.Optional;

public  interface RoomService {
    int createRoom(Room room);
    Room save(Room room);

    void setRoomReady(int roomId);
    List<Room> getAllRooms();
    List<Room> findAllByName(String name);
    Optional<Room> findRoomById(int id);
    boolean isRoomWithIdExist(int id);
    boolean isRoomWithNameExist(String name);
    boolean isRoomReady(int id);
    List<UserData> getUsersFromRoom(int id);
    void addUserInRoom(int userId, int roomId);
    public boolean isUserWithIdExistInRoom(int userId, int roomId);
    void deleteUserFromRoom(int userId);
}
