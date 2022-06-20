package com.example.carcassonne.web.controller;

import com.example.carcassonne.data.user.UserDataRepository;
import com.example.carcassonne.domain.model.Room;
import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.user.RoomService;
import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;
@Controller
public class GameLobbyController {
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;

    @GetMapping("/gameLobby")
    public String gameLobby(Model model) {
        try{
            String name =((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
            UserData userData= userService.findDataById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
            model.addAttribute("currentName",  name);
            model.addAttribute("currentRating","Рейтинг - " + userData.getRating());
            model.addAttribute("roomsList",roomService.getAllRooms());
        }catch (Exception e){

        }

        return "/gameLobby";
    }
}
