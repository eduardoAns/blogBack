package com.example.blogapi.repository;

import com.example.blogapi.models.Avatar;

public interface AvatarRepository {

    Avatar getAvatarByUserId(Integer idUser);

    void postAvatar(Avatar avatar);

    void updateAvatar(Avatar avatar);

    void deleteAvatarByUserId(Integer id);

    void deleteAvatar(String id);

}
