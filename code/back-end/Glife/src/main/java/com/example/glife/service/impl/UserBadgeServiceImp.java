package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.entity.UserBadge;
import com.example.glife.mapper.UserBadgeMapper;
import com.example.glife.service.UserBadgeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBadgeServiceImp extends ServiceImpl<UserBadgeMapper, UserBadge> implements UserBadgeService {

    @Override
    public List<UserBadge> getUserBadgesByUserId(Long userId) {
        LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserBadge::getUserId, userId);
        return list(queryWrapper);
    }

    @Override
    public UserBadge addUserBadge(UserBadge userBadge) {
        save(userBadge);
        return userBadge;
    }

    @Override
    public void deleteUserBadge(Long userId, Long badgeId) {
        LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserBadge::getUserId, userId).eq(UserBadge::getBadgeId, badgeId);
        remove(queryWrapper);
    }
}
