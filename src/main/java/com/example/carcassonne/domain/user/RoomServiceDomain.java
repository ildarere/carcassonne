package com.example.carcassonne.domain.user;

import com.example.carcassonne.data.rooms.RoomsRepository;
import com.example.carcassonne.domain.model.Room;
import com.example.carcassonne.domain.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceDomain implements RoomService{

    @Autowired
    RoomsRepository roomsRepository;

    @Override
    public void createRoom(Room room){
        roomsRepository.createRoom(room.getId(),room.getMaxSize(),room.getIsReady(),room.getName());
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

    @Override
    public List<Room> findAllById(int id) {
        return roomsRepository.findAllRoomsById(id);
    }

    @Override
    public boolean isRoomWithIdExist(int id) {
        return roomsRepository.countById(id) != 0 ? true : false;
    }

    @Override
    public boolean isRoomReady(int id) {
        return roomsRepository.isReady(id) == 1 ? true : false;
    }

    @Override
    public List<UserData> getUsersFromRoom(int id) {
        return roomsRepository.getUsersFromRoom(id);
    }
}
