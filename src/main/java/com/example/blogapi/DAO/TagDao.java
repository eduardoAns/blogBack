package com.example.blogapi.DAO;

import com.example.blogapi.models.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> getTags();

    void postTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag( Integer id);

    Tag getTagById(Integer id);

    Tag getTagByName(String name);

    boolean existTagById(Integer id);

    boolean existTagByName(String name);

    void postTagInPost(Integer id, Integer tagId);

    void updateTagInPost(Integer id, Integer tagId);

    boolean existPostInListTag(Integer idPost, Integer idTag);


}
