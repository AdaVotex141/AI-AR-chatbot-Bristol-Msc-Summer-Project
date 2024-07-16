package com.example.glife.service;

import com.example.glife.entity.UserBadge;

import java.util.List;

public interface UserBadgeService {
    List<UserBadge> getUserBadgesByUserId(Long userId);
    UserBadge addUserBadge(UserBadge userBadge);
    void deleteUserBadge(Long userId, Long badgeId);
}
