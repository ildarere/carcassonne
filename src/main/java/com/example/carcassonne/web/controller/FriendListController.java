package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.Friends;
import com.example.carcassonne.domain.model.User;
import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class FriendListController {
    @Autowired
    UserService userService;


    @GetMapping("/friends")
    public String friends(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();



        UserData userData= userService.findDataById(((UserDetailsImpl)principal).getId());
        model.addAttribute("currentName",  ((UserDetailsImpl) principal).getName());
        model.addAttribute("currentRating","Рейтинг - " + userData.getRating());
        model.addAttribute("friendList", userService.findFriendsListById(Math.toIntExact(((UserDetailsImpl) principal).getId())));

       //findByNameContaining();
//        System.out.println(.toString());
        return "/friends";
    }
    @PostMapping(path = "/friends/find", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserData> find(@RequestParam(name = "name", required = false)String name) {

        return userService.findByNameContaining(name);
    }



}

