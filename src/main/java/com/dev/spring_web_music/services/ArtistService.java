package com.dev.spring_web_music.services;

import com.dev.spring_web_music.controller.CateNotFoundException;
import com.dev.spring_web_music.model.Artist;
import com.dev.spring_web_music.model.Category;
import com.dev.spring_web_music.model.Song;
import com.dev.spring_web_music.repository.ArtistRepository;
import com.dev.spring_web_music.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired private ArtistRepository repo;

    public List<Artist> listAll() {
        return (List<Artist>) repo.findAll();
    }

    public void save(String artist_name, MultipartFile artist_image) throws IOException {
        Artist artist = new Artist();
        artist.setArtist_name(artist_name);

        String imageFileName = artist_image.getOriginalFilename();

        if(imageFileName.contains("..")) {
            System.out.println("not a valid file");
        }

        Path path = Paths.get("uploads/");
        InputStream inputStream = artist_image.getInputStream();
        Files.copy(inputStream, path.resolve(imageFileName), StandardCopyOption.REPLACE_EXISTING);
        artist.setArtist_image(imageFileName);


        repo.save(artist);
    }


    public Artist getId(Integer id_artist) throws CateNotFoundException {
        Optional<Artist> result = repo.findById(id_artist);
        if (result.isPresent()){
            return result.get();
        }
        throw new CateNotFoundException("Cound not find any category with id" + id_artist);
    }

    public void delete(Integer id_artist) throws CateNotFoundException {
        repo.deleteById(id_artist);
    }




}