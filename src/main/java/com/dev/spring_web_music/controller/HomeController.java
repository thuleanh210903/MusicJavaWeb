package com.dev.spring_web_music.controller;

import com.dev.spring_web_music.model.Song;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dev.spring_web_music.services.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/admin")
    public String checkLogin(HttpSession session, Model model) {
        if (session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            model.addAttribute("username", username);
            return "admin_index";
        } else {
            return "redirect:/auth";
        }
    }
    @GetMapping("/")
    public String gotoHome(){
        return "client/index";
    }


    @GetMapping("/about")
    public String showAboutPage() {
        return "client/about";
    }


}
