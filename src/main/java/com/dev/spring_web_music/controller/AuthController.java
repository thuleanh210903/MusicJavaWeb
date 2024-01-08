package com.dev.spring_web_music.controller;

import com.dev.spring_web_music.model.Admin;
import com.dev.spring_web_music.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Admin admin = userService.login(username, password);
        if (admin != null) {
            session.setAttribute("username", username);
            return "admin_index";
        } else {
            return "redirect:/auth";
        }
    }

    @GetMapping("/auth")
    public String showNewForm(Model model) {
        model.addAttribute("user", new Admin());
        return "auth/auth";
    }
}

