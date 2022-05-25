package com.example.carcassonne.domain.user;

import com.example.carcassonne.data.rooms.RoomsRepository;
import com.example.carcassonne.domain.model.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceDomain implements RoomService{
    @Autowired
    RoomsRepository roomsRepository;
    @Override
    public void createRoom(Room room){
        roomsRepository.createRoom(room.getRoomId(),room.getMaxSize(),room.getIsReady(),room.getName());
    }

    @Override
    public void setRoomReady(int roomId) {
        roomsRepository.setRoomReady(roomId);
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        roomsRepository.findAll().forEach(rooms::add);
        return rooms;
    }

    @Override
    public List<Room> findAllByName(String name) {
        return roomsRepository.findAllRoomsByName(name);
    }

}
