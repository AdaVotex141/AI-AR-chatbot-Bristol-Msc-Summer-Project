package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.R;
import com.example.glife.entity.*;
import com.example.glife.mapper.SystemRoutineMapper;
import com.example.glife.mapper.UserBadgeMapper;
import com.example.glife.mapper.UserMapper;
import com.example.glife.mapper.UserTreeMapper;
import com.example.glife.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public R<List<Long>> getUserBadgesByUserId(Long userId) {
        if (userExists(userId)) {
            LambdaQueryWrapper<UserBadge> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserBadge::getUserId, userId);
            List<UserBadge> userBadges = list(queryWrapper);

            List<Long> badgeIds = userBadges.stream()
                    .map(UserBadge::getBadgeId)
                    .collect(Collectors.toList());

            return R.success(badgeIds);
        } else {
            return R.error("User not found");
        }
    }

//    @Override
//    public R<UserBadge> addUserBadge(UserBadge userBadge) {
//        Long userId = userBadge.getUserId();
//        if (userExists(userId)) {
//            save(userBadge);
//            return R.success(userBadge);
//        } else {
//            return R.error("User not found");
//        }
//    }
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
                .eq(SystemRoutine::getSchedule, 0) // 0 表示日常任务
                .eq(SystemRoutine::getTick, 0); // 确保所有日常任务都未被完成

        List<SystemRoutine> dailyRoutines = systemRoutineMapper.selectList(queryWrapper);

        boolean allCompleted = dailyRoutines.stream().allMatch(task -> task.getTick() == 1);

        if (allCompleted) {
            // 检查用户是否已经获得了这个徽章
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 2L); // 2L 为 Daily Routine Starter Badge 的 ID

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // 授予徽章
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(2L);  // 2L 为 Daily Routine Starter Badge 的 ID
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
    public void checkAndAwardFirstTreePlanterBadge(Long userId) {
        // Check if the user has already planted a tree
        LambdaQueryWrapper<UserTree> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTree::getUserid, userId).gt(UserTree::getTreeSum, 0);

        UserTree userTree = userTreeMapper.selectOne(queryWrapper);

        if (userTree != null) {
            // Check if the user already has the badge
            LambdaQueryWrapper<UserBadge> badgeQueryWrapper = new LambdaQueryWrapper<>();
            badgeQueryWrapper.eq(UserBadge::getUserId, userId)
                    .eq(UserBadge::getBadgeId, 6L); //  badge ID 6 is for the First Tree Planter Badge

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // Award the badge
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(6L);  //  badge ID 6 is for the First Tree Planter Badge
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
                    .eq(UserBadge::getBadgeId, 7L); // badge ID 7 is for the Green Thumb Master Badge

            UserBadge existingBadge = baseMapper.selectOne(badgeQueryWrapper);

            if (existingBadge == null) {
                // Award the badge
                UserBadge newBadge = new UserBadge();
                newBadge.setUserId(userId);
                newBadge.setBadgeId(7L); // badge ID 7 is for the Green Thumb Master Badge
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