package com.example.blogapi.DAO;

import com.example.blogapi.models.Avatar;

import java.util.List;

public interface AvatarDao {

    Avatar getAvatarByUserId(Integer idUser);

    void postAvatar(Avatar avatar);

    void updateAvatar(Avatar avatar);

    void deleteAvatarByUserId(Integer id);

    void deleteAvatar(String id);

}
