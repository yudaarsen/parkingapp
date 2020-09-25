package ru.ikbo1018.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/")
@SessionAttributes("accountId")
public class HomeController {
    @GetMapping
    public String home(){
        return "home";
    }

    @ModelAttribute("accountId")
    public int accountId() {
        return 0;
    }
}
