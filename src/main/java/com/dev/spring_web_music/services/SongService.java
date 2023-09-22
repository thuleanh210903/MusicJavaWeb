package com.dev.spring_web_music.services;

import com.dev.spring_web_music.controller.CateNotFoundException;
import com.dev.spring_web_music.model.Category;
import com.dev.spring_web_music.model.Song;
import com.dev.spring_web_music.repository.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private SongRepository repo;

    @Autowired
    private ResourceLoader resourceLoader;

    public List<Song> listAll() {
        return (List<Song>) repo.findAll();
    }

    public void save(String song_name, String lyric, MultipartFile image, MultipartFile file_music, Integer id_category) throws IOException {
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


        String muisicFileName = file_music.getOriginalFilename();
        saveMusicFile(file_music, muisicFileName);
        song.setFile_music(muisicFileName);
        repo.save(song);


        repo.save(song);
    }

    public Song getId(Integer id_song) throws CateNotFoundException {
        Optional<Song> result = repo.findById(id_song);
        if (result.isPresent()){
            return result.get();
        }
        throw new CateNotFoundException("Cound not find any category with id" + id_song);
    }


    public String getCategoryName(Integer id_category)  throws CateNotFoundException{
        Optional<Category> category = categoryRepository.findById(id_category);
        if (category.isPresent()) {
            return category.get().getName_category();
        }
        throw new CateNotFoundException("Could not find any category with id: " + id_category);
    }

    public void delete(Integer id_song) throws CateNotFoundException {
        repo.deleteById(id_song);
    }


    public void saveMusicFile(MultipartFile file_music, String musicFileName) throws IOException {
        if (musicFileName.contains("..")) {
            System.out.println("Không phải là tệp hợp lệ");
        }
        String uploadDirectory = "upload_music";
        Path uploadPath = Paths.get(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path musicPath = uploadPath.resolve(musicFileName);
        try (InputStream inputStreamMusic = file_music.getInputStream()) {
            Files.copy(inputStreamMusic, musicPath, StandardCopyOption.REPLACE_EXISTING);
        }

    }

}
