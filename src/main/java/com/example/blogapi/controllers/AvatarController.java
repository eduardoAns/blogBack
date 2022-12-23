package com.example.blogapi.controllers;


import com.example.blogapi.DAO.AvatarDao;
import com.example.blogapi.models.Avatar;
import com.example.blogapi.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AvatarController {

    @Autowired
    private AvatarDao avatarDao;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="api/avatar/byUserId/{userId}")
    public Avatar getAvatarByUserId(@PathVariable Integer userId){
        return avatarDao.getAvatarByUserId(userId);
    }
}
