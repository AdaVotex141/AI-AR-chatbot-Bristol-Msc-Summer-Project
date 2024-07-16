package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.R;
import com.example.glife.entity.UserBadge;
import com.example.glife.mapper.UserBadgeMapper;
import com.example.glife.service.UserBadgeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBadgeServiceImp extends ServiceImpl<UserBadgeMapper, UserBadge> implements UserBadgeService {

    @Override
    public R<List<UserBadge>> getUserBadgesByUserId(Long userId) {
        LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserBadge::getUserId, userId);
        List<UserBadge> userBadges = list(queryWrapper);
        return R.success(userBadges);
    }

    @Override
    public R<UserBadge> addUserBadge(UserBadge userBadge) {
        save(userBadge);
        return R.success(userBadge);
    }

    @Override
    public R<String> deleteUserBadge(Long userId, Long badgeId) {
        LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserBadge::getUserId, userId).eq(UserBadge::getBadgeId, badgeId);
        boolean success = remove(queryWrapper);
        if (success) {
            return R.success("UserBadge deleted successfully");
        } else {
            return R.error("Failed to delete UserBadge");
        }
    }
}
