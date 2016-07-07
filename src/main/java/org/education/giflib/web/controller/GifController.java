package org.education.giflib.web.controller;

import org.education.giflib.model.Gif;
import org.education.giflib.service.CategoryService;
import org.education.giflib.service.GifService;
import org.education.giflib.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.education.giflib.web.FlashMessage.Status.SUCCESS;

@Controller
public class GifController {

    @Autowired
    private GifService gifService;

    @Autowired
    private CategoryService categoryService;

    // home page - list of all GIFs
    @RequestMapping("/")
    public String listGifs(Model model) {
        List<Gif> gifs = gifService.findAll();
        model.addAttribute("gifs", gifs);
        return "gif/index";
    }

    // single GIF page
    @RequestMapping("/gifs/{gifId}")
    public String gifDetails(@PathVariable Long gifId, Model model) {
        Gif gif = gifService.findById(gifId);
        model.addAttribute("gif", gif);
        return "gif/details";
    }

    // GIF image data
    @RequestMapping("gifs/{gifId}.gif")
    @ResponseBody
    public byte[] getImage(@PathVariable Long gifId) {
        return gifService.findById(gifId).getBytes();
    }

    // Upload a new GIF
    @RequestMapping(value = "/gifs", method = RequestMethod.POST)
    public String addGif(Gif gif, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        gifService.save(gif, file);
        // add a flash message for success
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("GIF successfully uploaded!", SUCCESS));
        return String.format("redirect:/gifs/%s", gif.getId());
    }

    // form for uploading a new GIF
    @RequestMapping("/upload")
    public String formNewGif(Model model) {
        if (!model.containsAttribute("gif")) {
            model.addAttribute("gif", new Gif());
        }
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("action", "/gifs");
        model.addAttribute("heading", "Upload");
        model.addAttribute("submit", "Add");

        return "gif/form";
    }

    // form for editing an existing GIF
    @RequestMapping(value = "/gifs/{gifId}/edit")
    public String formEditGif(@PathVariable Long gifId, Model model) {
        if (!model.containsAttribute("gif")) {
            model.addAttribute("gif", gifService.findById(gifId));
        }
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("action", String.format("/gifs/%s", gifId));
        model.addAttribute("heading", "Edit GIF");
        model.addAttribute("submit", "Update");

        return "gif/form";
    }

    // Delete a GIF
    @RequestMapping(value = "/gifs/{gifId}/delete", method = RequestMethod.POST)
    public String deleteGif(@PathVariable Long gifId, RedirectAttributes redirectAttributes) {
        Gif gif = gifService.findById(gifId);
        gifService.delete(gif);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("GIF deleted.", SUCCESS));
        return "redirect:/";
    }
}
