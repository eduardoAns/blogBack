package com.example.blogapi.controllers;


import com.example.blogapi.DAO.PostDao;
import com.example.blogapi.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
@RestController
public class PostController {

    @Autowired
    private PostDao postDao;

    @RequestMapping(value="api/posts", method = RequestMethod.GET)
    public List<Post> getPosts(){
        return postDao.getPosts();
    }

    @RequestMapping(value="api/post/{id}" , method = RequestMethod.GET)
    public Post getPostById(@PathVariable Integer id){
        return postDao.getPostById(id);
    }
}
