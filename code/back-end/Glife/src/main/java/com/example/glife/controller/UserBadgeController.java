package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.entity.Badge;
import com.example.glife.entity.UserBadge;
import com.example.glife.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_badges")
public class UserBadgeController {

    @Autowired
    private UserBadgeService userBadgeService;

    @GetMapping("/user/{userId}")
    public R<List<Badge>> getUserBadges(@PathVariable Long userId) {
        return userBadgeService.getUserBadgesByUserId(userId);
    }


    @DeleteMapping("/user/{userId}/badge/{badgeId}")
    public R<String> deleteUserBadge(@PathVariable Long userId, @PathVariable Long badgeId) {
        return userBadgeService.deleteUserBadge(userId, badgeId);
    }
}
