package com.example.glife.service;

import com.example.glife.common.R;
import com.example.glife.entity.SystemRoutine;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SystemRoutineService {
    public R<List<SystemRoutine>> init(HttpServletRequest request);

    public R<String> delete(HttpServletRequest request, Long id);

    public R<SystemRoutine> tick(HttpServletRequest request, Long id);

    public R<String> addFromAssistant(HttpServletRequest request);

    public R<String> addFromRandomTask(HttpServletRequest request, String content);

    public void reset();
}
