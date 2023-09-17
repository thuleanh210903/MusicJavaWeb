package com.dev.spring_web_music.controller;


import com.dev.spring_web_music.model.Category;
import com.dev.spring_web_music.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class CategoryController {
    @Autowired private CategoryService categoryService;

    @GetMapping("/categories")
   public String showCateList(Model model) {
        List<Category> categoryList = categoryService.listAll();
        model.addAttribute("categoryList", categoryList);
        return "categories";

   }

    @GetMapping("/categories/new")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle","Add New Category");
        return "cate_form";    }
    @PostMapping("/categories/save")
    public String saveCate (Category category) {
        categoryService.save(category);
        return "redirect:/categories" ;
    }

    @GetMapping("/categories/edit/{id_category}")
    public String showEditForm(@PathVariable("id_category")Integer id_category, Model model, RedirectAttributes re) {
        try {
            Category category = categoryService.getId(id_category);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle","Edit Category (ID: "+id_category+")");
            return "cate_edit";
        } catch (CateNotFoundException e) {
            re.addFlashAttribute("message", e.getMessage());
            throw new RuntimeException(e);
//            return "redirect:/categories";
        }
    }

    @GetMapping("/categories/delete/{id_category}")
    public String deleteCate(@PathVariable("id_category")Integer id_category, Model model, RedirectAttributes re) {
        try {
            categoryService.delete(id_category);

        } catch (CateNotFoundException e) {
            re.addFlashAttribute("message", e.getMessage());
            throw new RuntimeException(e);

        }
        return "redirect:/categories";
    }



}
