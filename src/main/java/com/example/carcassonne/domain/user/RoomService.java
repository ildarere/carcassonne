package com.example.carcassonne.domain.user;

import com.example.carcassonne.domain.model.Room;
import com.example.carcassonne.domain.model.UserData;

import java.util.List;

public  interface RoomService {
    void createRoom(Room room);
    void setRoomReady(int roomId);
    List<Room> getAllRooms();
    List<Room> findAllByName(String name);
    List<Room> findAllById(int id);
    boolean isRoomWithIdExist(int id);
    boolean isRoomReady(int id);
    List<UserData> getUsersFromRoom(int id);
}
