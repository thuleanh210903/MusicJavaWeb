package com.dev.spring_web_music.services;

import com.dev.spring_web_music.controller.CateNotFoundException;
import com.dev.spring_web_music.model.Song;
import com.dev.spring_web_music.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository repo;

    public List<Song> listAll() {
        return (List<Song>) repo.findAll();
    }

    public void save(Song song) {
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
