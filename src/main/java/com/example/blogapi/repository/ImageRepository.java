package com.example.blogapi.repository;

import com.example.blogapi.models.Image;

import java.util.List;

public interface ImageRepository {

    List<Image> getImages();

    Image getImageById(String id);

    void postImage(Image Image);

    void updateImage(Image image);

    void deleteImage(String id);

    boolean existImageById(String id);
}
