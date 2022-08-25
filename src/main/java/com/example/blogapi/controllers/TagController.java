package com.example.blogapi.controllers;


import com.example.blogapi.DAO.TagDao;
import com.example.blogapi.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
@RestController
public class TagController {

    @Autowired
    private TagDao tagDao;

    @RequestMapping(value="api/tags", method = RequestMethod.GET)
    public List<Tag> getTags(){
        return tagDao.getTags();
    }

    @RequestMapping(value="api/tag/{id}" , method = RequestMethod.GET)
    public Tag getTagById(@PathVariable Integer id){
        return tagDao.getTagById(id);

    }
}
