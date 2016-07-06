package org.education.giflib.web.controller;

import org.education.giflib.model.Gif;
import org.education.giflib.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GifController {
    @Autowired
    CategoryService categoryService;

    // form for uploading a new GIF
    @RequestMapping("/upload")
    public String formNewGif(Model model) {
        model.addAttribute("gif", new Gif());
        model.addAttribute("categories", categoryService.findAll());
        return "gif/form";
    }
}
