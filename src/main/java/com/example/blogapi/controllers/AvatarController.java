package com.example.blogapi.controllers;


import com.example.blogapi.models.Avatar;
import com.example.blogapi.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/avatar")
@RestController
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/byUserId/{userId}")
    public Avatar getAvatarByUserId(@PathVariable Integer userId){
        return avatarService.getAvatarByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping(value="/cloud/{id}")
    public Map deleteCloud(@PathVariable String id) throws IOException {

        return avatarService.deleteAvatarFromCloud(id);

    }
}
