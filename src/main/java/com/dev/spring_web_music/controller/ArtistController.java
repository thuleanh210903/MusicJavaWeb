package com.dev.spring_web_music.controller;


import com.dev.spring_web_music.model.Artist;

import com.dev.spring_web_music.model.Category;
import com.dev.spring_web_music.model.Song;
import com.dev.spring_web_music.services.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;


@Controller
public class ArtistController {
    @Autowired private ArtistService artistService;

    @GetMapping("/artists")
    public String showArtistList(Model model) {
        List<Artist> artistList = artistService.listAll();
        model.addAttribute("artistList", artistList);
        return "artist/artists";

    }

    @GetMapping("/artists/new")
    public String showNewForm(Model model) {
        model.addAttribute("artist", new Artist());
        model.addAttribute("pageTitle","Add New Artist");
        return "artist/artist_form";    }
    @PostMapping("/artists/save")
    public String saveArtist(@RequestParam("artist_name") String artist_name, @RequestParam("artist_image") MultipartFile artist_image) throws IOException {

        artistService.save(artist_name,artist_image);
        return "redirect:/artists";
    }
    @GetMapping("/artists/edit/{id_artist}")
    public String showEditForm(@PathVariable("id_artist")Integer id_artist, Model model, RedirectAttributes re) {
        try {
            Artist artist = artistService.getId(id_artist);
            model.addAttribute("artist", artist);
            model.addAttribute("pageTitle","Edit Artist (ID: "+id_artist+")");
            return "artist/artist_edit";
        } catch (CateNotFoundException e) {
            re.addFlashAttribute("message", e.getMessage());
            throw new RuntimeException(e);

        }
    }

    @GetMapping("/artists/delete/{id_artist}")
    public String deleteArtist(@PathVariable("id_artist")Integer id_artist, Model model, RedirectAttributes re) {
        try {
            artistService.delete(id_artist);

        } catch (CateNotFoundException e) {
            re.addFlashAttribute("message", e.getMessage());
            throw new RuntimeException(e);

        }
        return "redirect:/artists";
    }



    //client
    @GetMapping("/home")
    public String showAllArtist(Model model) {
        List<Artist> allArtist = artistService.listAll();
        model.addAttribute("allArtist", allArtist);

        return "client/index";
    }


    @GetMapping("/artist/song/{id_artist}")
    public String showSongOfArtist(Model model, @PathVariable("id_artist")Integer id_artist, RedirectAttributes re) {
        System.out.println(id_artist);
        try {
            List<Song> songs = artistService.songList(id_artist);
            model.addAttribute("songs", songs);
            System.out.println(songs);
            return "client/artistTrack";
        } catch (CateNotFoundException e) {
            re.addFlashAttribute("message", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}