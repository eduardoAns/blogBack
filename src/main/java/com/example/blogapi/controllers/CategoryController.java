package com.example.blogapi.controllers;

import com.example.blogapi.repository.CategoryRepository;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/categoria", method = RequestMethod.GET)
    public List<Categoria> getCategorias(){

        if(categoryRepository.getCategorias().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }

        return categoryRepository.getCategorias();
    };

}
