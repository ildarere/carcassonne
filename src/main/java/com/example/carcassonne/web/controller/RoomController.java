package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.user.RoomService;
import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;

    @GetMapping("/room{id}")
    public String  getById(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        if(!roomService.isRoomWithIdExist(id)){
            model.addAttribute("message", "комнаты не существует");
            return ("/room");
        }else if(roomService.isRoomReady(id)){
            model.addAttribute("message", "комнаты заполнена");
            return ("/room");
        }else {
            List<UserData> users = roomService.getUsersFromRoom(id);
            StringBuilder sb = new StringBuilder();
            for (UserData u: users              ) {
                sb.append(u.getId());
            }
            model.addAttribute("message", sb.toString());
            return ("/room");
        }

    }
    @MessageMapping("/room{id}/hello")
    @SendTo("/topic/room{id}/connected")
    public UserData connected( String message) throws Exception {
        UserData userData= userService.findDataById(1L);
        System.out.println(message);
        System.out.println(userData.toString());
        return userData;
    }

}
