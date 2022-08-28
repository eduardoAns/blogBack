package com.example.blogapi.controllers;


import com.example.blogapi.DAO.TagDao;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.ThreadContext.isEmpty;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
@RestController
public class TagController {

    @Autowired
    private TagDao tagDao;

    @RequestMapping(value="api/tags", method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> getTags(){
        if(tagDao.getTags().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tagDao.getTags());
    }

    @RequestMapping(value="api/tag/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Tag> getTagById(@PathVariable Integer id){
        if(!tagDao.existTagById(id)){
            throw new RequestException("P-400", HttpStatus.BAD_REQUEST,"id:"+id+" invalido, el tag no existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tagDao.getTagById(id));
    }

    @RequestMapping(value="api/tag" , method = RequestMethod.POST)
    public ResponseEntity postTag(@RequestBody Tag tag){

        if(tag.getId() != null){
            throw new RequestException("P-402", HttpStatus.BAD_REQUEST,"el id no es requerido");
        }

        if(tag.getNombre().equals("")|| tag.getNombre() == null){
            throw new RequestException("P-401", HttpStatus.BAD_REQUEST, "el nombre es requerido");
        }

        tagDao.postTag(tag);
        return  new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value="api/tag" , method = RequestMethod.PUT)
    public ResponseEntity updateTag(@RequestBody Tag tag){
        if(tag.getId().equals("")|| tag.getId() == null){
            throw new RequestException("P-402", HttpStatus.BAD_REQUEST,"el id es requerido");
        }

        if(tag.getNombre().equals("")|| tag.getNombre() == null){
            throw new RequestException("P-401", HttpStatus.BAD_REQUEST, "el nombre es requerido");
        }
        tagDao.updateTag(tag);
        return  new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value="api/tag/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity deleteTag(@PathVariable Integer id){
        if(!tagDao.existTagById(id)){
            throw new RequestException("P-400", HttpStatus.BAD_REQUEST,"id:"+id+" invalido, el tag no existe");
        }
        tagDao.deleteTag(id);
        return  new ResponseEntity(HttpStatus.OK);
    }
}
