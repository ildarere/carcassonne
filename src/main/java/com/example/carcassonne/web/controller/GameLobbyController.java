package com.example.carcassonne.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;
@Controller
public class GameLobbyController {

    @GetMapping("/gameLobby")
    public String gameLobby(Model model) {


        return "/gameLobby";
    }
}
