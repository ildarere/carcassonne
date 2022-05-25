package com.example.carcassonne.web.controller;

import com.example.carcassonne.data.rooms.RoomsRepository;
import com.example.carcassonne.domain.user.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/room{id}")
    public String  getById(@PathVariable int id, Model model) {
        if(!roomService.isRoomWithIdExist(id)){
            model.addAttribute("message", "комнаты не существует");
            return ("/room");
        }else if(roomService.isRoomReady(id)){
            model.addAttribute("message", "комнаты заполнена");
            return ("/room");
        }else {
            model.addAttribute("message", "ок");
            return ("/room");
        }

    }
}
