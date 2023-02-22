package com.example.blogapi.services;

import com.example.blogapi.models.Tag;
import org.springframework.validation.BindingResult;

import java.util.List;


public interface TagService {

    List <Tag> getAllTags();

    Tag getTagById(Integer id);

    void createTag (Tag tag, BindingResult result);

    void updateTag (Tag tag, BindingResult result);

    void deleteTag (Integer id);

    void addTagToPost (Integer postId, Integer tagId);
}
