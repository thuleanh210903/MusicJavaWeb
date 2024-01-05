package com.dev.spring_web_music.controller;

import com.dev.spring_web_music.model.Category;
import com.dev.spring_web_music.model.User;
import com.dev.spring_web_music.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired private UserService userService;


    @GetMapping("/auth")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/auth";    }

    @PostMapping("/auth/save")
    public String register(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("username") String username) {
        //            User existUser = userService.findUserByEmail(email);
//            if (existUser == null) {
//                userService.save(email, password, username);
//            } else {
//                throw new RuntimeException("User already exists");
//            }
        userService.save(email, password, username);
        return "redirect:/home";
    }





}
