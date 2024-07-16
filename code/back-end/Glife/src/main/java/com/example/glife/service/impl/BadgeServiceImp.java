package com.example.glife.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.R;
import com.example.glife.entity.Badge;
import com.example.glife.mapper.BadgeMapper;
import com.example.glife.service.BadgeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeServiceImp extends ServiceImpl<BadgeMapper, Badge> implements BadgeService {

    @Override
    public R<List<Badge>> getAllBadges() {
        List<Badge> badges = list();
        return R.success(badges);
    }

    @Override
    public R<Badge> getBadgeById(Long id) {
        Badge badge = getById(id);
        if (badge != null) {
            return R.success(badge);
        } else {
            return R.error("Badge not found");
        }
    }

    @Override
    public R<Badge> createBadge(Badge badge) {
        save(badge);
        return R.success(badge);
    }

    @Override
    public R<String> deleteBadge(Long id) {
        boolean success = removeById(id);
        if (success) {
            return R.success("Badge deleted successfully");
        } else {
            return R.error("Failed to delete badge");
        }
    }
}
