package com.teamtreehouse.giflib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GifController {

    @RequestMapping(value = "/")
    public String listGifs() {
        return "home";
    }
}
