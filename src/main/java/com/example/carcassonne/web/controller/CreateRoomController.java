package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.Room;
import com.example.carcassonne.domain.service.RoomService;
import com.example.carcassonne.web.form.room.RoomForm;
import com.example.carcassonne.web.form.room.RoomFormValidator;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CreateRoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomFormValidator validator;
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("/createRoom")
    public String gameLobby(Model model) {

        model.addAttribute("roomForm", new RoomForm());
        return "/createRoom";
    }
    @PostMapping("/createRoom")
    public String create(@ModelAttribute @Valid RoomForm room,
                         BindingResult result, Model model,
                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (result.hasErrors()) {
            return "/createRoom";
        } else {
            Room newRoom = new Room();
            newRoom.setName(room.getName());
            newRoom.setMaxSize(room.getMaxSize());
            newRoom = roomService.save(newRoom);
//            roomService.addUserInRoom(Math.toIntExact(userDetails.getId()), newRoom.getId());
            return "redirect:/room"+newRoom.getId();
        }


    }

}
