package com.dev.spring_web_music.repository;

import com.dev.spring_web_music.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Integer> {

//    @Query("SELECT c FROM categories c WHERE c.name_category LIKE '%p%'")
//    public List<Category> searchList(String keyword);

}
