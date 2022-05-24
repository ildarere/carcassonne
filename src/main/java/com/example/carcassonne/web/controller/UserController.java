package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.form.user.UserForm;
//import com.example.carcassonne.web.form.user.UserFormValidator;
import com.example.carcassonne.web.form.user.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserFormValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }



    @GetMapping(path = {"/user/registration/{userId}", "/user/registration"})
    public String userRegistration(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "/user/registration";
    }


    @PostMapping("/user/registration")
    public String userRegistrationSubmit(@ModelAttribute @Valid UserForm userForm,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/user/registration";
        } else {
            userService.update(userForm);
        }

        return "redirect:/";
    }

}
