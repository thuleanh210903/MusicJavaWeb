package com.dev.spring_web_music.controller;


import com.dev.spring_web_music.model.Category;
import com.dev.spring_web_music.model.Song;
import com.dev.spring_web_music.services.CategoryService;
import com.dev.spring_web_music.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private  CategoryService categoryService;

    @GetMapping("/songs")
    public String showSongList(Model model) {
        List<Song> songList = songService.listAll();

        List<String> categoryNames = new ArrayList<>();
        for (Song song : songList) {
            String categoryName = songService.getCategoryName(song.getId_category());
            categoryNames.add(categoryName);
        }
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("songList", songList);
        return "songs";
    }
    @GetMapping("/songs/new")
    public String showNewForm(Model model) {
        List<Category> categoryList = categoryService.listAll();
        model.addAttribute("song", new Song());
        model.addAttribute("pageTitle", "Add New Song");
        model.addAttribute("categories", categoryList);
        return "song_form";
    }

    @PostMapping("/songs/save")
    public String saveSong(@RequestParam("song_name") String song_name, @RequestParam("id_category") Integer id_category, @RequestParam("lyric") String lyric, @RequestParam("image") MultipartFile image,  @RequestParam("file_music") MultipartFile file_music) throws IOException {
        songService.save(song_name, lyric, image, file_music, id_category);
        return "redirect:/songs";
    }

    @GetMapping("/songs/edit/{id_song}")
    public String showEditForm(@PathVariable("id_song") Integer id_song, Model model, RedirectAttributes re) {
        try {
            Song song = songService.getId(id_song);
            model.addAttribute("song", song);
            model.addAttribute("pageTitle", "Edit Song(ID: " + id_song + ")");
            return "song_edit";
        } catch (CateNotFoundException e) {
            re.addFlashAttribute("message", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/songs/delete/{id_song}")
    public String deleteSong(@PathVariable("id_song") Integer id_song, Model model, RedirectAttributes re) {
        try {
            songService.delete(id_song);

        } catch (CateNotFoundException e) {
            re.addFlashAttribute("message", e.getMessage());
            throw new RuntimeException(e);

        }
        return "redirect:/songs";
    }
}
