package org.education.giflib.web.controller;

import org.education.giflib.model.Category;
import org.education.giflib.service.CategoryService;
import org.education.giflib.web.Color;
import org.education.giflib.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static org.education.giflib.web.FlashMessage.Status.FAILURE;
import static org.education.giflib.web.FlashMessage.Status.SUCCESS;

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

    // Add a category
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String addCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
            // add category if invalid was received
            redirectAttributes.addFlashAttribute("category", category);
            // redirect back to the form
            return "redirect:/categories/add";
        }
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category successfully added!", SUCCESS));
        return "redirect:/categories";
    }

    // Update an existing category
    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.POST)
    public String updateCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
            // add category if invalid was received
            redirectAttributes.addFlashAttribute("category", category);
            // redirect back to the form
            return String.format("redirect:/categories/%s/edit", category.getId());
        }
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category successfully updated!", SUCCESS));
        return "redirect:/categories";
    }

    // form for adding a new category
    @RequestMapping("categories/add")
    public String formNewCategory(Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", new Category());
        }
        model.addAttribute("colors", Color.values());
        model.addAttribute("action", "/categories");
        model.addAttribute("heading", "New Category");
        model.addAttribute("submit", "Add");
        return "category/form";
    }

    // form for editing an existing category
    @RequestMapping("categories/{categoryId}/edit")
    public String formEditCategory(@PathVariable Long categoryId, Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", categoryService.findById(categoryId));
        }
        model.addAttribute("colors", Color.values());
        model.addAttribute("action", String.format("/categories/%s", categoryId));
        model.addAttribute("heading", "Edit Category");
        model.addAttribute("submit", "Update");
        return "category/form";
    }

    // Delete an existing category
    @RequestMapping(value = "/categories/{categoryId}/delete", method = RequestMethod.POST)
    public String deleteCategory(@PathVariable Long categoryId, RedirectAttributes redirectAttributes) {
        Category cat = categoryService.findById(categoryId);
        if (cat.getGifs().size() > 0) {
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Only empty categories can be deleted.", FAILURE));
            return String.format("redirect:/categories/%s/edit", categoryId);
        }
        categoryService.delete(cat);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category deleted!", SUCCESS));
        return "redirect:/categories";
    }
}
