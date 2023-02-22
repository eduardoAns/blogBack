package com.example.blogapi.controllers;


import com.example.blogapi.models.Tag;
import com.example.blogapi.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<Tag> getTags(){
        return tagService.getAllTags();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/{id}")
    public Tag getTagById(@PathVariable Integer id){
        return tagService.getTagById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public void postTag(@Valid @RequestBody Tag tag, BindingResult result){
        tagService.createTag(tag, result);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @PutMapping
    public void updateTag(@Valid @RequestBody Tag tag, BindingResult result){
        tagService.updateTag(tag, result);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @DeleteMapping(value="/{id}")
    public void deleteTag(@PathVariable Integer id){
        tagService.deleteTag(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="/addToPost" , method = RequestMethod.POST)
    public void addTagToPost(@RequestParam Integer postId, @RequestParam Integer tagId){
        tagService.addTagToPost(postId, tagId);
    }

}
