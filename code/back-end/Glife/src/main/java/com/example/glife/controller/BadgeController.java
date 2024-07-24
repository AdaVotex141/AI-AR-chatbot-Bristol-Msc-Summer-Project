package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.entity.Badge;
import com.example.glife.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @GetMapping
    public R<List<Badge>> getAllBadges() {
        return badgeService.getAllBadges();
    }

    @GetMapping("/{id}")
    public R<Badge> getBadgeById(@PathVariable Long id) {
        return badgeService.getBadgeById(id);
    }

    @PostMapping
    public R<Badge> createBadge(@RequestBody Badge badge) {
        return badgeService.createBadge(badge);
    }

    @DeleteMapping("/{id}")
    public R<String> deleteBadge(@PathVariable Long id) {
        return badgeService.deleteBadge(id);
    }
}
