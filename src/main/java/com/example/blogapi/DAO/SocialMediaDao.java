package com.example.blogapi.DAO;

import com.example.blogapi.models.SocialMedia;

import java.util.List;

public interface SocialMediaDao {

    List<SocialMedia> getSocialMediasByUserId(Integer idUser);

    void postAvatar(SocialMedia avatar);

    void updateAvatar(SocialMedia avatar);

    void deleteAvatar(Integer id);
}
