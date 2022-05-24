package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @Autowired
    UserService userService;
    @GetMapping("/profile")
    public String profile(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentId =Math.toIntExact(((UserDetailsImpl) principal).getId());
        UserData userData= userService.findDataById(((UserDetailsImpl) principal).getId());

        //model.addAttribute("currentName",((UserDetailsImpl) principal).getUsername());

        model.addAttribute("name", userData.getName());
        model.addAttribute("rating","Рейтинг - " + userData.getRating());
        model.addAttribute("wins","Побед: " + userData.getWins());
        model.addAttribute("friends","Друзей: " + userService.countOfFriends(currentId) );
        model.addAttribute("gamesCount","Игр Сыграно: " + userData.getGamesCount() );
        model.addAttribute("currentName",  userData.getName());
        model.addAttribute("currentRating","Рейтинг - " + userData.getRating() );
        model.addAttribute("mail", ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());


        return "/profile";
    }
    @GetMapping("/profile1"  )
    public String idProfile(Model model){


        return "/profile1";
    }
}
