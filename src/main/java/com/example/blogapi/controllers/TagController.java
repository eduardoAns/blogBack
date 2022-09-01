package com.example.blogapi.controllers;


import com.example.blogapi.DAO.PostDao;
import com.example.blogapi.DAO.TagDao;
import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.ThreadContext.isEmpty;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TagController {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private PostDao postDao;

    @RequestMapping(value="api/tags", method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> getTags(){
        if(tagDao.getTags().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tagDao.getTags());
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/tag/{id}" , method = RequestMethod.GET)
    public Tag getTagById(@PathVariable Integer id){
        if(!tagDao.existTagById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el tag no existe","p-400");
        }
        return tagDao.getTagById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="api/tag" , method = RequestMethod.POST)
    public void postTag(@Valid @RequestBody Tag tag, BindingResult result){

        if(tag.getId() != null){
            throw new RequestException("P-402", HttpStatus.BAD_REQUEST,"el id no es requerido");
        }

        if(result.hasFieldErrors("nombre")){
            throw new RequestException("P-401", HttpStatus.BAD_REQUEST, "el nombre es requerido");
        }

        tagDao.postTag(tag);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @RequestMapping(value="api/tag" , method = RequestMethod.PUT)
    public void updateTag(@Valid @RequestBody Tag tag, BindingResult result){
        if(tag.getId().equals("")|| tag.getId() == null){
            throw new RequestException("P-402", HttpStatus.BAD_REQUEST,"el id es requerido");
        }

        if(result.hasFieldErrors("nombre")){
            throw new RequestException("P-401", HttpStatus.BAD_REQUEST, "el nombre es requerido");
        }
        tagDao.updateTag(tag);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @RequestMapping(value="api/tag/{id}" , method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable Integer id){
        if(!tagDao.existTagById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el tag no existe","p-404");
        }
        tagDao.deleteTag(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="api/tag/addToPost" , method = RequestMethod.POST)
    public void addTagToPost(@RequestParam Integer postId, @RequestParam Integer tagId){
        if(!postDao.existPostById(postId)){
            throw new NotFoundException("id:"+postId+" no encontrado, el post no existe","p-404");
        }
        if(!tagDao.existTagById(tagId)){
            throw new NotFoundException("id:"+tagId+" no encontrado, el tag no existe","p-404");
        }

        tagDao.postTagInPost(tagId, postId );
    }

}
