package com.dev.spring_web_music;


import com.dev.spring_web_music.model.Category;
import com.dev.spring_web_music.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTest {
    @Autowired private CategoryRepository repo;

    @Test
    public void testAddNew(){
        Category category = new Category();
        category.setName_category("pop");

        Category savedCate =  repo.save(category);

    }
}
