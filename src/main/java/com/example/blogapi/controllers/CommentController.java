package com.example.blogapi.controllers;


import com.example.blogapi.models.Comentario;
import com.example.blogapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/comentario")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<Comentario> getComments(){
        return commentService.getAllComments();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/{id}")
    public Comentario getCommentById(@PathVariable Integer id){
       return commentService.getCommentById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public void postComment(@Valid @RequestBody Comentario comment, BindingResult result){


        commentService.createComment(comment, result);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @PutMapping
    public void updateComment(@Valid @RequestBody Comentario comment, BindingResult result, @RequestHeader(required = false,value = "Authorization") String userToken){

        commentService.updateComment(comment, userToken, result);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @DeleteMapping(value="/{id}")
    public void deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/byPostId/{id}")
    public List<Comentario> getCommentByPostId(@PathVariable Integer id){
        return commentService.getCommentByPostId(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/ByUserId/{id}")
    public List<Comentario> getCommentByUser(@PathVariable Integer id){
        return commentService.getCommentByUserId(id);
    }
}











