package com.dev.spring_web_music.services;

import com.dev.spring_web_music.controller.CateNotFoundException;
import com.dev.spring_web_music.model.Category;
import com.dev.spring_web_music.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired private CategoryRepository repo;

    public List<Category> listAll() {
        return (List<Category>) repo.findAll();
    }

    public void save(Category category) {
        repo.save(category);
    }

    public Category getId(Integer id_category) throws CateNotFoundException {
        Optional<Category> result = repo.findById(id_category);
        if (result.isPresent()){
            return result.get();
        }
        throw new CateNotFoundException("Cound not find any category with id" + id_category);
    }

    public void delete(Integer id_category) throws CateNotFoundException {
        repo.deleteById(id_category);
    }



}
