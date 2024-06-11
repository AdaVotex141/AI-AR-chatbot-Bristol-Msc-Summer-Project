package com.example.glife.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.entity.User;
import com.example.glife.mapper.UserMapper;
import com.example.glife.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

}
