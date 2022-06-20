package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.user.RoomService;
import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
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
            return ("redirect:/gameLobby");
        }else if(roomService.isRoomReady(id)){
            model.addAttribute("message", "комнаты заполнена");
            return ("redirect:/gameLobby");
        }else {
            List<UserData> users = roomService.getUsersFromRoom(id);
            StringBuilder sb = new StringBuilder();
            for (UserData u: users              ) {
                sb.append(u.getId());
                System.out.println(u.toString());
            }
            model.addAttribute("message", sb.toString());
            model.addAttribute("users", users);
            return ("/room");
        }

    }
    @MessageMapping("/room{id}/hello")
    @SendTo("/topic/room{id}/connected")
    public UserData connected(@Payload String message,@DestinationVariable("id") int id, Principal principal) throws Exception {

        UserData userData= userService.findDataByEmail(principal.getName()).get();
        roomService.addUserInRoom(Math.toIntExact(userData.getId()), id);
        System.out.println(principal.getName());
        System.out.println(userData.toString());
        return userData;
    }
    @MessageMapping("/room{id}/userDisconnected")
    @SendTo("/topic/room{id}/userDisconnected")
    public int userDisconnect(@Payload int userId,@DestinationVariable("id") int id) throws Exception {
        //delete from db
        roomService.deleteUserFromRoom(userId, id);
        UserData userData= userService.findDataById((long) userId);
        System.out.println(userData.toString());
        return userId;
    }

}
