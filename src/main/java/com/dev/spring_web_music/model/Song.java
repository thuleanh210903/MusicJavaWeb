package com.dev.spring_web_music.model;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_song;

    @Column
    private String song_name;

    @Column
    private String lyric;

    @Column
    private String image;

    @Column
    private String file_music;

    @Column(insertable = false, updatable = false) // Add this annotation
    private Integer id_category;



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

    public String getFile_music() {
        return file_music;
    }

    public void setFile_music(String file_music) {
        this.file_music = file_music;
    }

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private Category category;
}
