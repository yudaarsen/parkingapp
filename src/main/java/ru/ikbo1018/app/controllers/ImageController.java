package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ikbo1018.app.data.ImageRepository;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping(value = "/{id}", produces = {"image/jpg", "image/png"})
    @ResponseBody
    public byte[] getImage(@PathVariable("id") int imageId) {
        return imageRepository.getImage(imageId).getData();
    }

}
