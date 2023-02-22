package com.example.blogapi.repository;

import com.example.blogapi.models.Tag;

import java.util.List;

public interface TagRepository {
    List<Tag> getTags();

    void postTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag( Integer id);

    Tag getTagById(Integer id);

    Tag getTagByName(String name);

    boolean existTagById(Integer id);

    boolean existTagByName(String name);

    void postTagInPost(Integer id, Integer tagId);

    List<Tag> getTagsByPostId(Integer id);

    void deleteTagInPost(Integer postId, Integer tagId);
}
