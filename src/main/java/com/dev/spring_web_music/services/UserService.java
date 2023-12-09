package com.dev.spring_web_music.services;

import com.dev.spring_web_music.controller.CateNotFoundException;
import com.dev.spring_web_music.model.Song;
import com.dev.spring_web_music.model.User;
import com.dev.spring_web_music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;



    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public void save(String email, String password, String username) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        repo.save(user);

    }

    public  User findUserByEmail(String email) throws CateNotFoundException {
        Optional<User> result = Optional.ofNullable(repo.findUserByEmail(email));
        if (result.isPresent()){
            return result.get();
        }
        throw new CateNotFoundException("Cound not find any user with id" + email);

    }



}
