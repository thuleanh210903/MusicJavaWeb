package com.dev.spring_web_music.repository;


import com.dev.spring_web_music.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
}
