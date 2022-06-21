package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.service.UserService;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String indexPage(Model model){
        try{
            String name =((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
            UserData userData= userService.findDataById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
            model.addAttribute("currentName",  name);
            model.addAttribute("rating","Рейтинг - " + userData.getRating());
        }catch (Exception e){

        }




        return "index";
    }
}
