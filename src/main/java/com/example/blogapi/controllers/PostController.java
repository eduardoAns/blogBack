package com.example.blogapi.controllers;


import com.example.blogapi.models.Post;
import com.example.blogapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<Post> getPosts(){
        return postService.getAllPosts();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/{id}")
    public Post getPostById(@PathVariable String id){

        System.out.println(id);

        return postService.getPostById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public void createPost(@Valid @RequestBody Post post, BindingResult result){
        postService.createPost(post, result);

    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping
    public void updatePost(@Valid @RequestBody Post post, BindingResult result){
        postService.updatePost(post, result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping
    public void deletePost(@PathVariable Integer id){
        postService.deletePost(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/categoria/{category}" )
    public List<Post> getPostsByCategory(@PathVariable String category){ return postService.getPostByCategory(category); }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/tag/{tag}")
    public List<Post> getPostsByTag(@PathVariable String tag){
        return  postService.getPostByTag(tag);
    }

    @GetMapping(value="/search/{title}")
    public List<Post> getPostByTitle(@PathVariable String title){ return  postService.getPostByTitle(title); }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/ByUserId/{id}")
    public List<Post> getPostByUser(@PathVariable Integer id){
        return postService.getPostByUser(id);
    }

}
