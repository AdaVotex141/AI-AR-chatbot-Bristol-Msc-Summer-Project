package com.example.glife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.glife.common.R;
import com.example.glife.entity.User;
import com.example.glife.entity.UserTree;

import javax.servlet.http.HttpServletRequest;

public interface UserTreeService  extends IService<UserTree> {
    public R<Integer> init(HttpServletRequest request);
    public R<Integer> update(HttpServletRequest request);
    public R<Integer> reUpdate(HttpServletRequest request);

}
