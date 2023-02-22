package com.example.blogapi.services;

import com.example.blogapi.models.Avatar;

import java.io.IOException;
import java.util.Map;

public interface AvatarService {

    Avatar getAvatarByUserId(Integer userId);

    Map deleteAvatarFromCloud (String id) throws IOException;
}
