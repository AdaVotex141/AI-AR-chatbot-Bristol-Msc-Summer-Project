package com.example.glife.service;

import com.example.glife.common.R;
import com.example.glife.entity.UserBadge;

import java.util.List;

public interface UserBadgeService {
    R<List<Long>> getUserBadgesByUserId(Long userId);
//    R<UserBadge> addUserBadge(UserBadge userBadge);
    R<String> deleteUserBadge(Long userId, Long badgeId);
    void checkAndAwardFirstTreePlanterBadge(Long userId);
    void checkAndAwardFirstTaskAchieverBadge(Long userId);
    void checkAndAwardDailyRoutineStarterBadge(Long userId);
    void checkAndAwardRoutineStreakMasterBadge(Long userId);
    void checkAndAwardMonthlyRoutineChampionBadge(Long userId);
    void checkAndAwardQuarterlyRoutineChampionBadge(Long userId);
    void checkAndAwardYearlyRoutineChampionBadge(Long userId);
    void checkAndAwardGreenThumbMasterBadge(Long userId);
    void checkAndAwardWeeklyWarriorBadge(Long userId);
    void checkAndAwardMonthlyMastermindBadge(Long userId);

}
