package com.example.blogapi.services;

import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.TagRepository;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TagServiceImp implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Tag> getAllTags() {
        if(tagRepository.getTags().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }
        return tagRepository.getTags();
    }

    @Override
    public Tag getTagById(Integer id) {
        if(!tagRepository.existTagById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el tag no existe","p-400");
        }
        return tagRepository.getTagById(id);
    }

    @Override
    public void createTag(Tag tag, BindingResult result) {
        if(tag.getId() != null){
            throw new RequestException("P-402", HttpStatus.BAD_REQUEST,"el id no es requerido");
        }

        if(result.hasFieldErrors("nombre")){
            throw new RequestException("P-401", HttpStatus.BAD_REQUEST, "el nombre es requerido");
        }

        tag.setFechaCreacion(LocalDateTime.now());

        tagRepository.postTag(tag);
    }

    @Override
    public void updateTag(Tag tag, BindingResult result) {

        if(tag.getId().equals("")|| tag.getId() == null){
            throw new RequestException("P-402", HttpStatus.BAD_REQUEST,"el id es requerido");
        }

        if(result.hasFieldErrors("nombre")){
            throw new RequestException("P-401", HttpStatus.BAD_REQUEST, "el nombre es requerido");
        }
        tagRepository.updateTag(tag);
    }

    @Override
    public void deleteTag(Integer id) {
        if(!tagRepository.existTagById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el tag no existe","p-404");
        }
        tagRepository.deleteTag(id);
    }

    @Override
    public void addTagToPost(Integer postId, Integer tagId) {
        if(!postRepository.existPostById(postId)){
            throw new NotFoundException("id:"+postId+" no encontrado, el post no existe","p-404");
        }
        if(!tagRepository.existTagById(tagId)){
            throw new NotFoundException("id:"+tagId+" no encontrado, el tag no existe","p-404");
        }

        tagRepository.postTagInPost(tagId, postId );
    }
}
