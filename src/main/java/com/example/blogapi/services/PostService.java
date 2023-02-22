package com.example.blogapi.services;

import com.example.blogapi.models.Post;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts ();

    List<Post> getPostByCategory(String category);

    List<Post> getPostByTag(String tag);

    List<Post> getPostByTitle (String title);

    List<Post> getPostByUser (Integer id);

    Post getPostById(String id);

    void createPost (Post post, BindingResult result);

    void updatePost (Post post, BindingResult result);

    void deletePost(Integer id);


}


