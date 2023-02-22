package com.example.blogapi.repository;

import com.example.blogapi.models.SocialMedia;

import java.util.List;

public interface SocialMediaRepository {

    List<SocialMedia> getSocialMediasByUserId(Integer idUser);

    void createSocialMedia(SocialMedia socialMedia);

    void updateSocialMedia(SocialMedia socialMedia);

    void deleteSocialMedia(SocialMedia socialMedia);
}
