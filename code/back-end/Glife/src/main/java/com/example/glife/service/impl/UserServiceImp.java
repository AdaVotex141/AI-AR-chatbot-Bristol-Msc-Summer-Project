package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.PasswordEncoder;
import com.example.glife.common.R;
import com.example.glife.entity.User;
import com.example.glife.mapper.UserMapper;
import com.example.glife.service.AssistantService;
import com.example.glife.service.RoutineService;
import com.example.glife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AssistantService assistantService;

    @Autowired
    private RoutineService routineService;

    /**
     *
     * @param request
     * @param user
     * @return
     */
    public R<String> register(HttpServletRequest request, User user) {
        // Check unique Name and Email
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User existingUser = baseMapper.selectOne(lambdaQueryWrapper);
        if (existingUser != null) {
            return R.error("Username already exists");
        }

        LambdaQueryWrapper<User> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.eq(User::getEmail, user.getEmail());
        User existingUser2 = baseMapper.selectOne(lambdaQueryWrapper2);
        if (existingUser2 != null) {
            return R.error("Email already exists");
        }

        // Register
        String inputPassword = user.getPassword();
        String encryptedPassword = passwordEncoder.encodePassword(inputPassword);
        String email = user.getEmail();

        // Implement user registration logic
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(email);
        newUser.setPassword(encryptedPassword);
        newUser.setCreateTime(LocalDateTime.now());
        baseMapper.insert(newUser);

        return R.success("Register success");
    }

    /**
     *
     * @param request
     * @param user
     * @return
     */
    public R<User> login(HttpServletRequest request, User user){
        String inputPassword = user.getPassword();
        String encryptedPassword = passwordEncoder.encodePassword(inputPassword);

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());

        User foundUser = getOne(lambdaQueryWrapper);

        if (foundUser == null) {
            return R.error("username not existed");
        }
        if(!passwordEncoder.matchPassword(inputPassword, foundUser.getPassword())){
            return R.error("password wrong");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", foundUser);

        foundUser.setLastLogin(LocalDateTime.now());
        updateById(foundUser);

        //create a new assistant after log in, and store it in session
        assistantService.initializeAssistant();
        session.setAttribute("assistantService", assistantService);
        //routineService.init(request);
        return R.success(foundUser);
    }

    /**
     *
     * @param request
     * @return
     */
    public R<String> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            if(session.getAttribute("assistantService") != null){
                session.removeAttribute("assistantService");
            }
        }
        return R.success("Successfully logout");
    }
}
