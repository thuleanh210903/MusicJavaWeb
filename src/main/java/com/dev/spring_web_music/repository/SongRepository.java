package com.dev.spring_web_music.repository;

import com.dev.spring_web_music.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SongRepository extends JpaRepository<Song, Integer> {}
