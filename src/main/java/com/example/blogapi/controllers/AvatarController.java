package com.example.blogapi.controllers;


import com.example.blogapi.DAO.AvatarDao;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.models.Avatar;
import com.example.blogapi.models.Categoria;
import com.example.blogapi.utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AvatarController {

    @Autowired
    private AvatarDao avatarDao;

    @Autowired
    CloudinaryUtil cloudinaryUtil;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="api/avatar/byUserId/{userId}")
    public Avatar getAvatarByUserId(@PathVariable Integer userId){
        return avatarDao.getAvatarByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/avatar/cloud/{id}" , method = RequestMethod.DELETE)
    public Map deleteCloud(@PathVariable String id) throws IOException {

        Map result = cloudinaryUtil.delete(id);
        avatarDao.deleteAvatar(id);

        return result;
    }
}
