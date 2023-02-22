package com.example.blogapi.services;


import com.example.blogapi.models.Image;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ImageService {

    List<Image> getAllImages();

    Image getImageById(String id);

    void postImage (Image image, BindingResult result);

    void updateImage(Image image, BindingResult result);

    void deleteImage(String id);

    Map uploadCloud (MultipartFile multipartFile) throws IOException;

    Map deleteCloud (String id) throws IOException;


}
