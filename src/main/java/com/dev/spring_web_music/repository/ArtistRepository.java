package com.dev.spring_web_music.repository;

import com.dev.spring_web_music.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {


}
