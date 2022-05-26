package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class TestController {
    @GetMapping("/test")
    public String  getById(Model model) {
        return "/test";
    }

    @MessageMapping("/test/hello")
    @SendTo("/topic/test/greetings")
    public String greeting(String message) throws Exception {

        System.out.println(message);
        return ("Hello, " + message + "!");
    }

}
