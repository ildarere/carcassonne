package com.example.carcassonne.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

public class RoomListController {
    @GetMapping("/gameLobby")
    public String RoomsList(Model model, Locale locale) {


        return "/gameLobby";
    }
}
