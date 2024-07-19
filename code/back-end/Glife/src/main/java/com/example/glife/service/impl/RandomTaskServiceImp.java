package com.example.glife.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.entity.RandomTask;
import com.example.glife.mapper.RandomTaskMapper;
import com.example.glife.service.RandomTaskService;
import org.springframework.stereotype.Service;

@Service
public class RandomTaskServiceImp extends ServiceImpl<RandomTaskMapper, RandomTask> implements RandomTaskService{
}
