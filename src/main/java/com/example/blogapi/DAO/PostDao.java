package com.example.blogapi.DAO;

import com.example.blogapi.models.Post;


import java.util.List;

public interface PostDao {
    List<Post> getPosts();

    Post getPostById(Integer id);
}
