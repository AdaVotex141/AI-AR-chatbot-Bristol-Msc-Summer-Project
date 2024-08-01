package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.R;
import com.example.glife.entity.*;
import com.example.glife.mapper.*;
import com.example.glife.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBadgeServiceImp extends ServiceImpl<UserBadgeMapper, UserBadge> implements UserBadgeService {

    @Autowired
    private UserTreeMapper userTreeMapper;
    @Autowired
    private SystemRoutineMapper systemRoutineMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BadgeMapper badgeMapper;

    @Override
    public R<List<Badge>> getUserBadgesByUserId(Long userId) {
        if (userExists(userId)) {
            LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserBadge::getUserId, userId);
            List<UserBadge> userBadges = list(queryWrapper);

            List<Long> badgeIds = userBadges.stream()
                    .map(UserBadge::getBadgeId)
                    .collect(Collectors.toList());

            if (!badgeIds.isEmpty()) {
                LambdaQueryWrapper<Badge> badgeQueryWrapper = new LambdaQueryWrapper<>();
                badgeQueryWrapper.in(Badge::getId, badgeIds);
                List<Badge> badges = badgeMapper.selectList(badgeQueryWrapper);

                return R.success(badges);
            } else {
                return R.success(Collections.emptyList());
            }
        } else {
            return R.error("User not found");
        }
    }

    @Transactional
    public void checkAndAwardFirstTaskAchieverBadge(Long userId) {
        LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserBadge::getUserId, userId)
                .eq(UserBadge::getBadgeId, 1L); // Badge ID 1 for First Task Achiever Badge

        UserBadge existingBadge = baseMapper.selectOne(queryWrapper);

        if (existingBadge == null) {
            // Award the badge
            UserBadge newBadge = new UserBadge();
            newBadge.setUserId(userId);
            newBadge.setBadgeId(1L);  // Badge ID 1 for First Task Achiever Badge
            newBadge.setEarnedTime(LocalDateTime.now());

            baseMapper.insert(newBadge);
        }
    }
    @Transactional
    public void checkAndAwardDailyRoutineStarterBadge(Long userId) {
        // 检查用户是否已经完成所有日常任务
        LambdaQueryWrapper<SystemRoutine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemRoutine::getUserid, userId)
                .eq(SystemRoutine::getSchedule, 0)
                .eq(SystemRoutine::getTick, 0);

        List<SystemRoutine> dailyRoutines = systemRoutineMapper.selectList(queryWrapper);

        boolean allCompleted = dailyRoutines.stream().allMatch(task -> task.getTick() == 1);

        if (allCompleted) {
            // 检查用户是否已经获得了这个徽章
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 2L); 

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // 授予徽章
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(2L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }
    @Transactional
    public void checkAndAwardRoutineStreakMasterBadge(Long userId) {
        User user = userMapper.selectById(userId);
        if (user.getLoginDays() >= 7) {
            LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 3L);

            UserBadge existingBadge = baseMapper.selectOne(queryWrapper);

            if (existingBadge == null) {
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(3L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }
    @Transactional
    public void checkAndAwardMonthlyRoutineChampionBadge(Long userId) {
        User user = userMapper.selectById(userId);
        if (user.getLoginDays() >= 30) {
            LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 4L);

            UserBadge existingBadge = baseMapper.selectOne(queryWrapper);

            if (existingBadge == null) {
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(4L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }
    @Transactional
    public void checkAndAwardQuarterlyRoutineChampionBadge(Long userId) {
        User user = userMapper.selectById(userId);
        if (user.getLoginDays() >= 90) {
            LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 5L);

            UserBadge existingBadge = baseMapper.selectOne(queryWrapper);

            if (existingBadge == null) {
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(5L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }
    public void checkAndAwardYearlyRoutineChampionBadge(Long userId) {
        User user = userMapper.selectById(userId);
        if (user.getLoginDays() >= 365) {
            LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 6L);

            UserBadge existingBadge = baseMapper.selectOne(queryWrapper);

            if (existingBadge == null) {
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(6L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }
    @Transactional
    public void checkAndAwardFirstTreePlanterBadge(Long userId) {
        // Check if the user has already planted a tree
        LambdaQueryWrapper<UserTree> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTree::getUserid, userId).gt(UserTree::getTreeSum, 0);

        UserTree userTree = userTreeMapper.selectOne(queryWrapper);

        if (userTree != null) {
            // Check if the user already has the badge
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 7L);

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // Award the badge
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(7L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }
    @Transactional
    public void checkAndAwardGreenThumbMasterBadge(Long userId) {
        // Check if the user has planted 5 trees
        LambdaQueryWrapper<UserTree> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTree::getUserid, userId).ge(UserTree::getTreeSum, 5);

        UserTree userTree = userTreeMapper.selectOne(queryWrapper);

        if (userTree != null) {
            // Check if the user already has the badge
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 8L);

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // Award the badge
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(8L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }
    @Transactional
    public void checkAndAwardWeeklyWarriorBadge(Long userId) {
        // Check if the user has already completed a weekly routine task
        LambdaQueryWrapper<SystemRoutine> routineQueryWrapper = new LambdaQueryWrapper<>();
        routineQueryWrapper.eq(SystemRoutine::getUserid, userId).eq(SystemRoutine::getSchedule, 1).eq(SystemRoutine::getTick, 1);

        List<SystemRoutine> userRoutines = systemRoutineMapper.selectList(routineQueryWrapper);

        if (!userRoutines.isEmpty()) {
            // Check if the user already has the badge
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 9L);

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // Award the badge
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(9L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }

    @Transactional
    public void checkAndAwardMonthlyMastermindBadge(Long userId) {
        // Check if the user has already completed a monthly routine task
        LambdaQueryWrapper<SystemRoutine> routineQueryWrapper = new LambdaQueryWrapper<>();
        routineQueryWrapper.eq(SystemRoutine::getUserid, userId).eq(SystemRoutine::getSchedule, 2).eq(SystemRoutine::getTick, 1);

        List<SystemRoutine> userRoutines = systemRoutineMapper.selectList(routineQueryWrapper);

        if (!userRoutines.isEmpty()) {
            // Check if the user already has the badge
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 10L);

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // Award the badge
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(10L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }

    @Transactional
    public void checkAndAwardGreenChallengeMasterBadge(Long userId) {
        // Check the number of different daily routines added by the user
        LambdaQueryWrapper<SystemRoutine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemRoutine::getUserid, userId);

        List<SystemRoutine> routines = systemRoutineMapper.selectList(queryWrapper);
        long distinctRoutineCount = routines.stream().map(SystemRoutine::getContent).distinct().count();

        if (distinctRoutineCount >= 5) {
            // Check if the user already has the badge
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 11L);

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // Award the badge
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(11L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }
    @Transactional
    public void checkAndAwardEcoMilestoneBadge(Long userId) {
        // Check the number of different daily routines added by the user
        LambdaQueryWrapper<SystemRoutine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemRoutine::getUserid, userId);

        List<SystemRoutine> routines = systemRoutineMapper.selectList(queryWrapper);
        long distinctRoutineCount = routines.stream().map(SystemRoutine::getContent).distinct().count();

        if (distinctRoutineCount >= 10) {
            // Check if the user already has the badge
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 12L);

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // Award the badge
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(12L);
                newBadge.setEarnedTime(LocalDateTime.now());

                baseMapper.insert(newBadge);
            }
        }
    }

    @Override
    public R<String> deleteUserBadge(Long userId, Long badgeId) {
        if (userExists(userId)) {
            LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, badgeId);
            boolean success = remove(queryWrapper);
            if (success) {
                return R.success("UserBadge deleted successfully");
            } else {
                return R.error("Failed to delete UserBadge");
            }
        } else {
            return R.error("User not found");
        }
    }
    private boolean userExists(Long userId) {
        // Logic to check if user exists in the database
        return userId != null && userId > 0;
    }
    private Long getUserID(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = null;
        Long userid = null;
        if(session != null && session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }
        userid = user.getId();
        return userid;
    }
}
