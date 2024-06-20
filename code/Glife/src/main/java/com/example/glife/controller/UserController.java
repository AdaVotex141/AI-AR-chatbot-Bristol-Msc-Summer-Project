package com.example.glife.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.glife.common.PasswordEncoder;
import com.example.glife.common.R;
import com.example.glife.entity.User;
import com.example.glife.service.AssistantService;
import com.example.glife.service.RoutineService;
import com.example.glife.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * Register new User
     */
    @PostMapping("/register")
    public R<String> register(HttpServletRequest request, @RequestBody User user){
        return userService.register(request,user);
    }

    /**
     * Login
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        return userService.login(request,user);
    }

    /**
     * Logout
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        return userService.logout(request);
    }
}
