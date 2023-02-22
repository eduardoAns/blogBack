package com.example.blogapi.repository;

import com.example.blogapi.models.Image;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.Tag;


import java.util.List;

public interface PostRepository {
    List<Post> getPosts();

    Post getPostById(Integer id);

    void createPost(Post post);

    void updateDetailsPost (Post post);

    void deletePost(Integer id);

    Integer getPostIdByTitle(String title);

    boolean existPostById(Integer id);

    List<Post> getPostsByCategory(String category);

    List<Post> getPostsByTag(String tag);

    List<Post> getPostsByTitle(String title);

    List<Post> getPostsByUserId(Integer id);
    
}
