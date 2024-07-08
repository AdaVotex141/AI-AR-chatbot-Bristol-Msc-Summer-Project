package com.example.glife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.glife.common.R;
import com.example.glife.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {

    public R<String> register(HttpServletRequest request, User user, String code);

    public R<User> login(HttpServletRequest request, User user);

    public R<String> logout(HttpServletRequest request);

    public R<String> sendCode(HttpServletRequest request, String email) throws MessagingException;

}
