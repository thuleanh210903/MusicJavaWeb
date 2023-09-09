package com.dev.spring_web_music.services;

import com.dev.spring_web_music.controller.CateNotFoundException;
import com.dev.spring_web_music.model.Song;
import com.dev.spring_web_music.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository repo;

    @Autowired
    private ResourceLoader resourceLoader;

    public List<Song> listAll() {
        return (List<Song>) repo.findAll();
    }

    public void save(String song_name, String lyric, MultipartFile image, Integer id_category) throws IOException {
        Song song = new Song();
        song.setSong_name(song_name);
        song.setLyric(lyric);
        song.setId_category(id_category);
        String imageFileName = image.getOriginalFilename();

        if(imageFileName.contains("..")) {
            System.out.println("not a valid file");
        }

        Path path = Paths.get("uploads/");
        InputStream inputStream = image.getInputStream();
        Files.copy(inputStream, path.resolve(imageFileName), StandardCopyOption.REPLACE_EXISTING);
        song.setImage(imageFileName);
        repo.save(song);
    }

    public Song getId(Integer id_song) throws CateNotFoundException {
        Optional<Song> result = repo.findById(id_song);
        if (result.isPresent()){
            return result.get();
        }
        throw new CateNotFoundException("Cound not find any category with id" + id_song);
    }

    public void delete(Integer id_song) throws CateNotFoundException {
        repo.deleteById(id_song);
    }

}
