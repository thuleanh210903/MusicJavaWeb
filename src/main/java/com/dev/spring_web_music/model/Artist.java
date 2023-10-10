package com.dev.spring_web_music.model;

import jakarta.persistence.*;
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_artist;

    @Column
    private String artist_name;

    public Integer getId_artist() {
        return id_artist;
    }

    public void setId_artist(Integer id_artist) {
        this.id_artist = id_artist;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getArtist_image() {
        return artist_image;
    }

    public void setArtist_image(String artist_image) {
        this.artist_image = artist_image;
    }

    @Column
    private String artist_image;
}
