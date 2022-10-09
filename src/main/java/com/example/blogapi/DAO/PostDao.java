package com.example.blogapi.DAO;

import com.example.blogapi.models.Post;


import java.util.List;

public interface PostDao {
    List<Post> getPosts();

    Post getPostById(Integer id);

    void postPost(Post post);
    
    void updatePost(Post post);

    void deletePost(Integer id);

    boolean existPostById(Integer id);

    List<Post> getPostsByCategory(String category);

    List<Post> getPostsByTag(String tag);

    List<String> getIdPosts();
    
}
