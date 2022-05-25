package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.Friends;
import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("isFriend", "Это ваша страница");
        model.addAttribute("disable", "true");
        model.addAttribute("id",  currentId);

        return "/profile";
    }

    @GetMapping("/id{id}")
    public String  getById(@PathVariable int id, Model model) {
        if(userService.isUserWithIdExist(id)){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            int currentId = id;

            UserData userData= userService.findDataById((long) currentId);
            List<Friends> friends = userService.findFriendsById(Math.toIntExact(((UserDetailsImpl) principal).getId()));
            boolean isFriend= false;
            for (Friends f: friends) {
                if(currentId==f.getFirstFriend() || currentId==f.getSecondFriend()){
                    model.addAttribute("isFriend", "Пользователь уже у вас в друзьях");
                    model.addAttribute("disable", "true");
                    isFriend= true;
                }
            }
            if(!isFriend){
                model.addAttribute("isFriend", "false");
            }
            model.addAttribute("name", userData.getName());
            model.addAttribute("rating","Рейтинг - " + userData.getRating());
            model.addAttribute("wins","Побед: " + userData.getWins());
            model.addAttribute("friends","Друзей: " + userService.countOfFriends(currentId) );
            model.addAttribute("gamesCount","Игр Сыграно: " + userData.getGamesCount() );
            model.addAttribute("id",  currentId);
            model.addAttribute("currentName",  ((UserDetailsImpl)principal).getName());
            model.addAttribute("currentRating","Рейтинг - " + userService.findDataById((long) currentId).getRating() );
        }else {
            model.addAttribute("name", "Страницы этого пользователя не существует");
            model.addAttribute("disable", "true");
        }


        return "/profile";
    }
    @PostMapping(path = "/id/addFriend", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addFriend(@RequestParam(name = "q", required = false)int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentId =Math.toIntExact(((UserDetailsImpl) principal).getId());
        userService.addFriend(currentId, id);
    }
}
