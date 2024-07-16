package com.example.glife.controller;

import com.example.glife.entity.UserBadge;
import com.example.glife.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_badges")
public class UserBadgeController {

    @Autowired
    private UserBadgeService userBadgeService;

    @GetMapping("/user/{userId}")
    public List<UserBadge> getUserBadges(@PathVariable Long userId) {
        return userBadgeService.getUserBadgesByUserId(userId);
    }

    @PostMapping
    public UserBadge addUserBadge(@RequestBody UserBadge userBadge) {
        return userBadgeService.addUserBadge(userBadge);
    }

    @DeleteMapping("/user/{userId}/badge/{badgeId}")
    public void deleteUserBadge(@PathVariable Long userId, @PathVariable Long badgeId) {
        userBadgeService.deleteUserBadge(userId, badgeId);
    }
}
