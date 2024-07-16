package com.example.glife.service;

import com.example.glife.common.R;
import com.example.glife.entity.Badge;

import java.util.List;

public interface BadgeService {
    R<List<Badge>> getAllBadges();
    R<Badge> getBadgeById(Long id);
    R<Badge> createBadge(Badge badge);
    R<String> deleteBadge(Long id);
}
