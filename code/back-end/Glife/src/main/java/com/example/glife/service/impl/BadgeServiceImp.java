package com.example.glife.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.entity.Badge;
import com.example.glife.mapper.BadgeMapper;
import com.example.glife.service.BadgeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeServiceImp extends ServiceImpl<BadgeMapper, Badge> implements BadgeService {

    @Override
    public List<Badge> getAllBadges() {
        return list();
    }

    @Override
    public Badge getBadgeById(Long id) {
        return getById(id);
    }

    @Override
    public Badge createBadge(Badge badge) {
        save(badge);
        return badge;
    }

    @Override
    public void deleteBadge(Long id) {
        removeById(id);
    }
}
