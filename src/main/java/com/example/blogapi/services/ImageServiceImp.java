package com.example.blogapi.services;

import com.example.blogapi.repository.ImageRepository;
import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Image;
import com.example.blogapi.utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImp implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    CloudinaryUtil cloudinaryUtil;


    @Override
    public List<Image> getAllImages() {
        if(imageRepository.getImages().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }
        return imageRepository.getImages();
    }

    @Override
    public Image getImageById(String id) {
        if(!imageRepository.existImageById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }

        return imageRepository.getImageById(id);
    }

    @Override
    public void postImage(Image image, BindingResult result) {
        if(image.getId() != null){
            throw new BadRequestException("el id no es requerido","P-400");
        }

        if( result.hasFieldErrors("idPost")){
            throw new BadRequestException("el idPost es requerido","P-400");
        }

        //if subtitulo hasfielderror return bad request
        if( result.hasFieldErrors("url")){
            throw new BadRequestException("el url es requerido","P-400");
        }

        imageRepository.postImage(image);
    }

    @Override
    public void updateImage(Image image, BindingResult result) {
        if(result.hasFieldErrors("id")){
            throw new BadRequestException("el id es requerido","P-400");
        }

        if( result.hasFieldErrors("idPost")){
            throw new BadRequestException("el idPost es requerido","P-400");
        }

        //if subtitulo hasfielderror return bad request
        if( result.hasFieldErrors("url")){
            throw new BadRequestException("el url es requerido","P-400");
        }

        imageRepository.updateImage(image);
    }

    @Override
    public void deleteImage(String id) {
        if(!imageRepository.existImageById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }
        imageRepository.deleteImage(id);
    }

    @Override
    public Map uploadCloud(MultipartFile multipartFile) throws IOException {
        System.out.println("********************");
        System.out.println(multipartFile);
        Map result = cloudinaryUtil.upload(multipartFile);
        System.out.println(result);
        return result;
    }

    @Override
    public Map deleteCloud(String id) throws IOException {
        System.out.println("**********delete*********");
        System.out.println(id);
        Map result = cloudinaryUtil.delete(id);
        if(!imageRepository.existImageById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, la imagen no existe en bd","p-404");
        }
        imageRepository.deleteImage(id);
        return result;
    }
}
