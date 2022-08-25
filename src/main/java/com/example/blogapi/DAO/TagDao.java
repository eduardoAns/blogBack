package com.example.blogapi.DAO;

import com.example.blogapi.models.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> getTags();

    Tag getTagById(Integer id);
}
