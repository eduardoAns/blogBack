package com.example.blogapi.services;

import com.example.blogapi.repository.AvatarRepository;
import com.example.blogapi.models.Avatar;
import com.example.blogapi.utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class AvatarServiceImp implements AvatarService{

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    CloudinaryUtil cloudinaryUtil;

    @Override
    public Avatar getAvatarByUserId(Integer userId) {
        return avatarRepository.getAvatarByUserId(userId);
    }

    @Override
    public Map deleteAvatarFromCloud(String id) throws IOException {
        Map result = cloudinaryUtil.delete(id);
        avatarRepository.deleteAvatar(id);

        return result;
    }
}
