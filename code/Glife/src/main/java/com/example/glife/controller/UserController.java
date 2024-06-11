package com.example.glife.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.glife.common.R;
import com.example.glife.entity.User;
import com.example.glife.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public R<User> register(HttpServletRequest request, @RequestBody User user){
        // Implement user registration logic
        // userService.register(user);
        return null;
    }

    /**
     * Login
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user){
        String password = user.getPassword();

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());

        User foundUser = userService.getOne(lambdaQueryWrapper);

        if(foundUser == null || !foundUser.getPassword().equals(password)){
            return R.error("Login failed");
        }

        // Set user session
        HttpSession session = request.getSession();
        session.setAttribute("user", foundUser);

        // Update user in database if necessary
        userService.updateById(foundUser);

        return R.success(foundUser);
    }

    /**
     * Logout
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
        return R.success("Successfully logout");
    }
}
