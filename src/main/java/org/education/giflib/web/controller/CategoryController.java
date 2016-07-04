package org.education.giflib.web.controller;

import org.education.giflib.model.Category;
import org.education.giflib.service.CategoryService;
import org.education.giflib.web.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Index of all categories
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String listCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String addCategory(Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    // Form for adding a new category
    @RequestMapping("categories/add")
    public String formNewCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("colors", Color.values());
        return "category/form";
    }
}
