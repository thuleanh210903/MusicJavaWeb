package com.dev.spring_web_music.repository;

import com.dev.spring_web_music.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
