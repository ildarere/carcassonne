package com.example.carcassonne.domain.user;

import com.example.carcassonne.domain.model.Room;

import java.util.List;

public  interface RoomService {
    void createRoom(Room room);
    void setRoomReady(int roomId);
    List<Room> getAllRooms();
    List<Room> findAllByName(String name);
}
