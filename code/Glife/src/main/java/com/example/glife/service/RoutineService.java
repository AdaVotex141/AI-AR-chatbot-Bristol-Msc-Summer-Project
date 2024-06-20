package com.example.glife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.glife.common.R;
import com.example.glife.entity.Routine;
import com.example.glife.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface RoutineService extends IService<Routine> {

    public R<String> add(HttpServletRequest request, Routine routine);

    public R<Routine> update(HttpServletRequest request, Routine routine);

    public R<Routine> tick(HttpServletRequest request, Routine routine);

    public R<String> init(HttpServletRequest request);
}
