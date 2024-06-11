package com.example.glife.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.glife.common.PasswordEncoder;
import com.example.glife.common.R;
import com.example.glife.entity.User;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register new User
     */
    @PostMapping("/register")
    public R<String> register(HttpServletRequest request, @RequestBody User user){
        String name = user.getUsername();
        //check unique Name and Email
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User existingUser = userService.getOne(lambdaQueryWrapper);
        if(existingUser != null){
            return R.error("Username already exists");
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.eq(User::getEmail, user.getEmail());
        User existingUser2 = userService.getOne(lambdaQueryWrapper2);
        if(existingUser2 != null){
            return R.error("Email already exists");
        }

        //Register
        String inputPassword = user.getPassword();
        String encryptedPassword = passwordEncoder.encodePassword(inputPassword);
        String email = user.getEmail();
        // Implement user registration logic
        User newUser = new User();
        newUser.setUsername(name);
        newUser.setEmail(email);
        newUser.setPassword(encryptedPassword);
        newUser.setCreateTime(LocalDateTime.now());
        userService.save(newUser);




        return R.success("register success");
    }

    /**
     * Login
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        String inputPassword = user.getPassword();
        String encryptedPassword = passwordEncoder.encodePassword(inputPassword);

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());

        User foundUser = userService.getOne(lambdaQueryWrapper);

        //TODO admin for test only :
        if (foundUser != null && foundUser.getUsername().equals("admin") && inputPassword.equals("bris12345")) {
            HttpSession session = request.getSession();
            session.setAttribute("user", foundUser);
            return R.success(foundUser);
        }

        if (foundUser == null || !passwordEncoder.matchPassword(inputPassword, foundUser.getPassword())) {
            return R.error("Login failed");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", foundUser);

        foundUser.setLastLogin(LocalDateTime.now());
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
