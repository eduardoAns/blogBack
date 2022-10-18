package com.example.blogapi.controllers;


import com.example.blogapi.DAO.PostDao;
import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Comentario;
import com.example.blogapi.models.Image;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PostController {

    @Autowired
    private PostDao postDao;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/post", method = RequestMethod.GET)
    public List<Post> getPosts(){
        if(postDao.getPosts().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }

        return postDao.getPosts();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/post/{id}" , method = RequestMethod.GET)
    public Post getPostById(@PathVariable Integer id){

        if(!postDao.existPostById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }

        return postDao.getPostById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="api/post" , method = RequestMethod.POST)
    public void postPost(@Valid @RequestBody Post post, BindingResult result){
        if(post.getId() != null){
            throw new BadRequestException("el id no es requerido","P-400");
        }

        if( result.hasFieldErrors("titulo")){
            throw new BadRequestException("el titulo es requerido","P-400");
        }

        //if subtitulo hasfielderror return bad request
        if( result.hasFieldErrors("subtitulo")){
            throw new BadRequestException("el subtitulo es requerido","P-400");
        }

        if( result.hasFieldErrors("contenido")){
            throw new BadRequestException("el contenido es requerido","P-400");
        }

        //if fechaCreacion hasfielderror return bad request
        if( result.hasFieldErrors("fechaCreacion")){
            throw new BadRequestException("la fecha de creacion es requerida","P-400");
        }

        //if estado hasfielderror return bad request
        if( result.hasFieldErrors("estado")){
            throw new BadRequestException("el estado es requerido","P-400");
        }

        //if idUsuario hasfielderror return bad request
        if( result.hasFieldErrors("idUsuario")){
            throw new BadRequestException("el id de usuario es requerido","P-400");
        }

        //if idCategoria hasfielderror return bad request
        if( result.hasFieldErrors("idCategoria")){
            throw new BadRequestException("el id de categoria es requerido","P-400");
        }
            List<Image> Images = post.getImages();
            List<Tag> Tags = post.getTags();

            Post postForm = new Post();
            postForm.setTitulo(post.getTitulo());
            postForm.setSubtitulo(post.getSubtitulo());
            postForm.setContenido(post.getContenido());
            postForm.setFechaCreacion(post.getFechaCreacion());
            postForm.setEstado(post.getEstado());
            postForm.setIdUsuario(post.getIdUsuario());
            postForm.setCategoria(post.getCategoria());

            postDao.postPost(postForm, Images, Tags);

    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/post" , method = RequestMethod.PUT)
    public void updatePost(@Valid @RequestBody Post post, BindingResult result){
        if(result.hasFieldErrors("id")){
            throw new BadRequestException("el id es requerido","P-400");
        }

        if( result.hasFieldErrors("titulo")){
            throw new BadRequestException("el id es requerido","P-400");
        }
        //if subtitulo hasfielderror return bad request
        if( result.hasFieldErrors("subtitulo")){
            throw new BadRequestException("el subtitulo es requerido","P-400");
        }

        if( result.hasFieldErrors("contenido")){
            throw new BadRequestException("el contenido es requerido","P-400");
        }

        //if imagen hasfielderror return bad request
        if( result.hasFieldErrors("imagen")){
            throw new BadRequestException("el imagen es requerido","P-400");
        }

        //if fechaCreacion hasfielderror return bad request
        if( result.hasFieldErrors("fechaCreacion")){
            throw new BadRequestException("la fecha de creacion es requerida","P-400");
        }

        //if estado hasfielderror return bad request
        if( result.hasFieldErrors("estado")){
            throw new BadRequestException("el estado es requerido","P-400");
        }

        //if idUsuario hasfielderror return bad request
        if( result.hasFieldErrors("idUsuario")){
            throw new BadRequestException("el id de usuario es requerido","P-400");
        }

        //if idCategoria hasfielderror return bad request
        if( result.hasFieldErrors("idCategoria")){
            throw new BadRequestException("el id de categoria es requerido","P-400");
        }

        List<Image> Images = post.getImages();
        List<Tag> Tags = post.getTags();

        Post postForm = new Post();
        postForm.setId(post.getId());
        postForm.setTitulo(post.getTitulo());
        postForm.setSubtitulo(post.getSubtitulo());
        postForm.setContenido(post.getContenido());
        postForm.setFechaCreacion(post.getFechaCreacion());
        postForm.setEstado(post.getEstado());
        postForm.setIdUsuario(post.getIdUsuario());
        postForm.setCategoria(post.getCategoria());

        postDao.updatePost(post, Images, Tags);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/post/{id}" , method = RequestMethod.DELETE)
    public void deletePost(@PathVariable Integer id){
        if(!postDao.existPostById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }
        postDao.deletePost(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/post/categoria/{category}" , method = RequestMethod.GET)
    public List<Post> getPostsByCategory(@PathVariable String category){ return  postDao.getPostsByCategory(category); }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/post/tag/{tag}" , method = RequestMethod.GET)
    public List<Post> getPostsByTag(@PathVariable String tag){
        return  postDao.getPostsByTag(tag);
    }

    @RequestMapping(value="api/post/search/{title}" , method = RequestMethod.GET)
    public List<Post> getPostByTitle(@PathVariable String title){ return  postDao.getPostsByTitle(title); }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "api/post/listaId", method = RequestMethod.GET)
    public List<String> getListaId(){
        return postDao.getIdPosts();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/post/ByUserId/{id}" , method = RequestMethod.GET)
    public List<Post> getOrdenByUser(@PathVariable Integer id){
        return postDao.getPostsByUserId(id);
    }

}
