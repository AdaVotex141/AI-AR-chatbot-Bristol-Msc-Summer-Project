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

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * Register new User
     */
    @PostMapping("/register")
    public R<String> register(HttpServletRequest request, @RequestBody User user, @RequestParam String code){
        log.info("---------User register-------------");
        return userService.register(request,user,code);
    }

    /**
     * Login
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        log.info("---------User login---------");
        return userService.login(request,user);
    }

    /**
     * Logout
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        return userService.logout(request);
    }


    @PostMapping("/sendCode")
    public R<String> sendCode(HttpServletRequest request, @RequestBody String email) throws MessagingException {
        return userService.sendCode(request,email);
    }
}
