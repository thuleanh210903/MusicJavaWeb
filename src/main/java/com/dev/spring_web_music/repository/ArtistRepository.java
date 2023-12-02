package com.dev.spring_web_music.repository;

import com.dev.spring_web_music.model.Artist;
import com.dev.spring_web_music.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    @Query("SELECT s FROM Song s JOIN FETCH s.artist WHERE s.artist.id_artist = :id_artist")
    List<Song> listSongsByArtist(@Param("id_artist") Integer id_artist);


}
