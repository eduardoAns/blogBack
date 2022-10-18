package com.example.blogapi.DAO;

import com.example.blogapi.models.Image;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.Tag;


import java.util.List;

public interface PostDao {
    List<Post> getPosts();

    Post getPostById(Integer id);

    void postPost(Post post, List<Image> images, List<Tag> tags);
    
    void updatePost(Post post, List<Image> images, List<Tag> tags);

    void deletePost(Integer id);

    boolean existPostById(Integer id);

    List<Post> getPostsByCategory(String category);

    List<Post> getPostsByTag(String tag);

    List<Post> getPostsByTitle(String title);

    List<String> getIdPosts();

    List<Post> getPostsByUserId(Integer id);
    
}
