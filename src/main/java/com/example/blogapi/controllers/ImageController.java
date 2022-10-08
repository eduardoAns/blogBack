package com.example.blogapi.controllers;

import com.example.blogapi.DAO.ImageDao;
import com.example.blogapi.DAO.PostDao;
import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Image;
import com.example.blogapi.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ImageController {
    @Autowired
    private ImageDao imageDao;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/image", method = RequestMethod.GET)
    public List<Image> getImages(){
        if(imageDao.getImages().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }
        return imageDao.getImages();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/image/{id}" , method = RequestMethod.GET)
    public Image getImageById(@PathVariable Integer id){

        if(!imageDao.existImageById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }

        return imageDao.getImageById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="api/image" , method = RequestMethod.POST)
    public void postImage(@Valid @RequestBody Image image, BindingResult result){
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


        imageDao.postImage(image);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/image" , method = RequestMethod.PUT)
    public void updateImage(@Valid @RequestBody Image image, BindingResult result){
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

        imageDao.updateImage(image);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/image/{id}" , method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable Integer id){
        if(!imageDao.existImageById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }
        imageDao.deleteImage(id);
    }
}
