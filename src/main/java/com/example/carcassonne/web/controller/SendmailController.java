package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.mail.MailClient;
import com.example.carcassonne.domain.user.UserService;
import com.example.carcassonne.web.form.user.UserForm;
import com.example.carcassonne.web.spring.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Random;

@Controller
public class SendmailController {
    @Autowired
    private MailClient mail;
    @Autowired
    private UserService userService;
    private long id;
    @GetMapping("/send-mail")
    public String sendMail(Model model, @ModelAttribute("user") UserForm user) {
        this.id= userService.getIdByEmail(user.getEmail());
        Random random= new Random(id);
        int code=random.nextInt(1000,9999);
        mail.sendTestEmail(user.getEmail(), code);
        return "verify";
    }
    @PostMapping("/send-mail/verifyCode")
    @ResponseBody
    public String verify( @RequestParam(name = "code", required = false)int codeNew){
        Random random= new Random(id);
        int code=random.nextInt(1000,9999);
        System.out.println(code+" - code\n" +
                codeNew + " - new");
        if(code==codeNew){
            userService.setEnabledTrue(id);
            return "true";
        }
        else {
            return "false";
        }
    }

}
