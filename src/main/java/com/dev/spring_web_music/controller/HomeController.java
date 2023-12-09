package com.dev.spring_web_music.controller;

import com.dev.spring_web_music.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dev.spring_web_music.services.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/admin")
    public String showHomeAdminPage() {

        return "index";
    }



    @GetMapping("/about")
    public String showAboutPage() {
        return "client/about";
    }






}
