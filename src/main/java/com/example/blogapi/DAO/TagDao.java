package com.example.blogapi.DAO;

import com.example.blogapi.models.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> getTags();

    void postTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag( Integer id);

    Tag getTagById(Integer id);

    boolean existTagById(Integer id);
}
