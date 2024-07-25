package com.example.glife.service.impl;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.glife.common.R;
import com.example.glife.entity.Statistics;
import com.example.glife.entity.SystemRoutine;
import com.example.glife.entity.User;
import com.example.glife.mapper.SystemRoutineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class StatisticsServiceImp {
    @Autowired
    SystemRoutineMapper mapper;

    public R<Statistics> init(HttpServletRequest request) {
        Statistics stat = new Statistics();
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        Long userID = user.getId();



        // Query for each schedule and tick = 1
        int dailyCount = mapper.selectCount(
                new LambdaQueryWrapper<SystemRoutine>()
                        .eq(SystemRoutine::getUserid, userID)
                        .eq(SystemRoutine::getSchedule, 0)
                        .eq(SystemRoutine::getTick, 1)
        );

        int weeklyCount = mapper.selectCount(
                new LambdaQueryWrapper<SystemRoutine>()
                        .eq(SystemRoutine::getUserid, userID)
                        .eq(SystemRoutine::getSchedule, 1)
                        .eq(SystemRoutine::getTick, 1)
        );

        int monthlyCount = mapper.selectCount(
                new LambdaQueryWrapper<SystemRoutine>()
                        .eq(SystemRoutine::getUserid, userID)
                        .eq(SystemRoutine::getSchedule, 2)
                        .eq(SystemRoutine::getTick, 1)
        );

        // Calculate total counts for each schedule type
        int totalDaily = mapper.selectCount(
                new LambdaQueryWrapper<SystemRoutine>()
                        .eq(SystemRoutine::getUserid, userID)
                        .eq(SystemRoutine::getSchedule, 0)
        );

        int totalWeekly = mapper.selectCount(
                new LambdaQueryWrapper<SystemRoutine>()
                        .eq(SystemRoutine::getUserid, userID)
                        .eq(SystemRoutine::getSchedule, 1)
        );

        int totalMonthly = mapper.selectCount(
                new LambdaQueryWrapper<SystemRoutine>()
                        .eq(SystemRoutine::getUserid, userID)
                        .eq(SystemRoutine::getSchedule, 2)
        );

        // Calculate percentages
        double dailyPercentage = totalDaily > 0 ? (double) dailyCount / totalDaily * 100 : 0;
        double weeklyPercentage = totalWeekly > 0 ? (double) weeklyCount / totalWeekly * 100 : 0;
        double monthlyPercentage = totalMonthly > 0 ? (double) monthlyCount / totalMonthly * 100 : 0;

        dailyPercentage = Double.parseDouble(String.format("%.2f", dailyPercentage));
        weeklyPercentage = Double.parseDouble(String.format("%.2f", weeklyPercentage));
        monthlyPercentage = Double.parseDouble(String.format("%.2f", monthlyPercentage));

// Set the statistics object
        stat.setDaily(dailyPercentage);
        stat.setWeekly(weeklyPercentage);
        stat.setMonthly(monthlyPercentage);

        return R.success(stat);
    }


}
