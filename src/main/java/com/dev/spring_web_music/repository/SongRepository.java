package com.dev.spring_web_music.repository;

import com.dev.spring_web_music.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query("SELECT s FROM Song s JOIN FETCH s.category")
    List<Song> listAllWithCategory();


    @Query("SELECT s FROM Song s JOIN FETCH s.artist")
    List<Song> listAllWithArtist();
}
