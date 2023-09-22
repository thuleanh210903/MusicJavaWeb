package com.dev.spring_web_music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadMusicController {
    private static final String MUSIC_DIRECTORY = "upload_music";

    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getMusic(@PathVariable("fileName") String fileName) {
        try {
            Path filePath = Paths.get(MUSIC_DIRECTORY, fileName);
            if (Files.exists(filePath)) {
                byte[] buffer = Files.readAllBytes(filePath);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("audio/mpeg")).body(byteArrayResource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
