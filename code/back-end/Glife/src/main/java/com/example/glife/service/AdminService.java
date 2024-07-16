package com.example.glife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.glife.common.R;
import com.example.glife.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService extends IService<Admin> {
    public R<Admin> login(HttpServletRequest request, Admin admin);
    public R<String> add(HttpServletRequest request, Admin admin);
    public R<String> changePermission(HttpServletRequest request, Admin admin);
    public R<List<Admin>> init(HttpServletRequest request);


}
