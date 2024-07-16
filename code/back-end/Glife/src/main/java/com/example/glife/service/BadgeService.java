package com.example.glife.service;

import com.example.glife.entity.Badge;

import java.util.List;

public interface BadgeService {
    List<Badge> getAllBadges();
    Badge getBadgeById(Long id);
    Badge createBadge(Badge badge);
    void deleteBadge(Long id);
}
