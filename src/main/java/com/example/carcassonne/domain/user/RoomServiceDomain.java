package com.example.carcassonne.domain.user;

import com.example.carcassonne.data.rooms.RoomRepository;
import com.example.carcassonne.domain.model.Room;
import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.web.form.room.RoomForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceDomain implements RoomService{

    @Autowired
    RoomRepository roomsRepository;

    @Override
    public Room save(Room room) {
        return roomsRepository.save(room);
    }


    @Override
    public int createRoom(Room room){
       return roomsRepository.createRoom(room.getId(),room.getMaxSize(),room.getIsReady(),room.getName());
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
    public Optional<Room> findRoomById(int id) {
        System.out.println(roomsRepository.countById(id));
        return roomsRepository.findRoomById(id);
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

    @Override
    public void addUserInRoom(int userId, int roomId) {
        roomsRepository.addUserInRoom(userId, roomId);
    }

    @Override
    public boolean isRoomWithNameExist(String name) {
        return roomsRepository.countByName(name) != 0 ? true : false;
    }

    @Override
    public void deleteUserFromRoom(int userId, int roomId) {
        roomsRepository.deleteUserFromRoom(userId, roomId);
    }
}
