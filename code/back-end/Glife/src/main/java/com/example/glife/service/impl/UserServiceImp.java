package com.example.glife.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.PasswordEncoder;
import com.example.glife.common.R;
import com.example.glife.common.RedisConstants;
import com.example.glife.entity.User;
import com.example.glife.mapper.UserMapper;
import com.example.glife.service.AssistantService;
import com.example.glife.service.RoutineService;
import com.example.glife.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.glife.common.RedisConstants.LOGIN_CODE_KEY;
import static com.example.glife.common.RedisConstants.LOGIN_CODE_TTL;

@Service
@Slf4j
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AssistantService assistantService;

    @Autowired
    private RoutineService routineService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EmailServiceImp emailServiceImp;

    /**
     *
     * @param request
     * @param user
     * @return
     */
    public R<String> register(HttpServletRequest request, User user, String code) {
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

        //verify code
        String redisCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY+email);
        if(redisCode == null || !redisCode.equals(code)){
            return R.error("verify code error");
        }

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

        //check if last_Login and now is different date
        LocalDateTime lastLogin = foundUser.getLastLogin();
        if(lastLogin == null || isConsecutiveDays(lastLogin)){
            foundUser.setLoginDays(foundUser.getLoginDays()+1);
        }else{
            foundUser.setLoginDays(1);
        }

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

    public R<String> sendCode(HttpServletRequest request, String email) throws MessagingException {
        //check email
        EmailValidator validator = EmailValidator.getInstance();
        if(!validator.isValid(email)){
            return R.error("Email form not correct!");
        }
        String code = RandomUtil.randomNumbers(6);
        log.info("the verify code is:{}", code);

        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + email, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        try {
            emailServiceImp.sendVerificationEmail(email, code);
        } catch (MessagingException e) {
            log.error("Failed to send verification email to {}: {}", email, e.getMessage());
            return R.error("Failed to send verification email");
        }
        return R.success("");
    }

    private boolean isConsecutiveDays(LocalDateTime lastLogin){
        LocalDate now = LocalDate.now();
        LocalDate lastLoginDate = lastLogin.toLocalDate();

        return now.minusDays(1).isEqual(lastLoginDate);
    }

/*    public R<String> updateLoginDays(HttpServletRequest request){
        //get user
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User)session.getAttribute("user");
        }else{
            return R.error("can't find user");
        }
        //check last_login time
        if (user != null){

        }




            return R.success("update login days when tick success");
    }*/
}
