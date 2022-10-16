package com.example.blogapi.DAO;

import com.example.blogapi.models.Image;
import com.example.blogapi.models.Post;

import java.util.List;

public interface ImageDao {

    List<Image> getImages();

    Image getImageById(String id);

    void postImage(Image Image);

    void updateImage(Image image);

    void deleteImage(String id);

    boolean existImageById(String id);
}
