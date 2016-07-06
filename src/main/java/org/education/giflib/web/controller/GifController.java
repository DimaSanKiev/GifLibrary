package org.education.giflib.web.controller;

import org.education.giflib.model.Gif;
import org.education.giflib.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GifController {
    @Autowired
    CategoryService categoryService;

    // upload a new GIF
    @RequestMapping(value = "/gifs", method = RequestMethod.POST)
    public String addGif(@RequestParam MultipartFile file) {
        return null;
    }

    // form for uploading a new GIF
    @RequestMapping("/upload")
    public String formNewGif(Model model) {
        model.addAttribute("gif", new Gif());
        model.addAttribute("categories", categoryService.findAll());
        return "gif/form";
    }
}
