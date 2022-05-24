package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.Friends;
import com.example.carcassonne.domain.model.User;
import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class FriendListController {
    @Autowired
    UserService userService;


    @GetMapping("/friends")
    public String friends(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal!=null){
            int currentId =Math.toIntExact(((UserDetailsImpl) principal).getId());
            List<Friends> fr =userService.findFriendsById(currentId);
            for (Friends i: fr) {
                if(i.getFirstFriend()==currentId){
                }
            }
        }


        UserData userData= userService.findDataById(((UserDetailsImpl)principal).getId());
        model.addAttribute("currentName",  ((UserDetailsImpl) principal).getName());
        model.addAttribute("currentRating","Рейтинг - " + userData.getRating());



//        System.out.println(userService.findByNameContaining("%a%").toString());
        return "/friends";
    }



}

