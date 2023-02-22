package com.example.blogapi.controllers;

import com.example.blogapi.models.Image;
import com.example.blogapi.services.ImageService;
import com.example.blogapi.utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="api/image")
@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    CloudinaryUtil cloudinaryUtil;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<Image> getImages(){
        return imageService.getAllImages();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/{id}")
    public Image getImageById(@PathVariable String id){
        return imageService.getImageById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public void postImage(@Valid @RequestBody Image image, BindingResult result){
        imageService.postImage(image, result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping
    public void updateImage(@Valid @RequestBody Image image, BindingResult result){
        imageService.updateImage(image, result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping(value="/{id}")
    public void deleteImage(@PathVariable String id){
        imageService.deleteImage(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping(value = "/cloud")
    public Map postCloud(@RequestBody MultipartFile multipartFile) throws IOException {

        return imageService.uploadCloud(multipartFile);
    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping(value = "/cloud/{id}")
    public Map deleteCloud(@PathVariable String id) throws IOException {
        return imageService.deleteCloud(id);
    }
}
