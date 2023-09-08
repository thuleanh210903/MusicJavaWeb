package com.dev.spring_web_music.model;


import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_song;

    @Column
    private String song_name;

    public Integer getId_song() {
        return id_song;
    }

    public void setId_song(Integer id_song) {
        this.id_song = id_song;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
    }

    @Column
    private String lyric;

    @Column
    private String image;

    @Column
    private Integer id_category;
}
