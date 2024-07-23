package com.example.glife.service;

import com.example.glife.common.R;
import com.example.glife.entity.RandomTask;
import com.example.glife.entity.SystemRoutine;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SystemRoutineService {
    public R<List<SystemRoutine>> init(HttpServletRequest request);

    public R<String> delete(HttpServletRequest request, Long id);

    public R<SystemRoutine> tick(HttpServletRequest request, Long id);

    public R<String> addFromAssistant(HttpServletRequest request, int schedule);

    public R<String> addFromRandomTask(HttpServletRequest request, SystemRoutine routine);

    public R<String> updateSchedule(HttpServletRequest request, SystemRoutine systemRoutine);

    public void resetDaily();

    public void resetWeekly();

    public void resetMonthly();
}
