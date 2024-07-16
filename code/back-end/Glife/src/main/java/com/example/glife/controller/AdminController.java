package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.entity.Admin;
import com.example.glife.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody  Admin admin){
        return adminService.login(request,admin);
    }

    @RequestMapping("/add")
    public R<String> add(HttpServletRequest request,@RequestBody Admin admin){
       return adminService.add(request,admin);
    }

    @RequestMapping("/remove")
    public R<String> changePermission(HttpServletRequest request,@RequestBody Admin admin){
        return adminService.changePermission(request, admin);
    }
    @GetMapping("/init")
    public R<List<Admin>> init(HttpServletRequest request){
        return adminService.init(request);
    }


}
