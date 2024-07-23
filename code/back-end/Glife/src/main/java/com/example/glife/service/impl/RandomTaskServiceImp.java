package com.example.glife.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.MessageWsHandler;
import com.example.glife.common.R;
import com.example.glife.entity.*;
import com.example.glife.mapper.RandomTaskMapper;
import com.example.glife.mapper.RoutineMapper;
import com.example.glife.service.RandomTaskService;
import com.example.glife.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RandomTaskServiceImp extends ServiceImpl<RandomTaskMapper, RandomTask> implements RandomTaskService {
    @Autowired
    MessageWsHandler wsHandler;

    public R<String> send(HttpServletRequest request, RandomTask randomTask){

        StringBuilder task = buildTask(randomTask);
        wsHandler.broadCast(task.toString());
        //websocket session

        //store the message in SQL
        RandomTask storeRandomTask = new RandomTask();
        HttpSession session = request.getSession(false);
        Admin admin = null;
        if(session != null && session.getAttribute("admin") != null){
            admin = (Admin) session.getAttribute("admin");
        }else{
            return R.error("user hasn't login");
        }

        storeRandomTask.setCreater(admin.getUsername());
        storeRandomTask.setCreateTime(LocalDateTime.now());
        storeRandomTask.setDescription(randomTask.getDescription());
        storeRandomTask.setTitle(randomTask.getTitle());
        baseMapper.insert(randomTask);

        return R.success("Send successfully");
    }

    public R<List<RandomTask>> init(HttpServletRequest request){
        List<RandomTask> taskList = new ArrayList<>();

        LambdaQueryWrapper<RandomTask> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(RandomTask::getCreateTime);

        taskList = baseMapper.selectList(lambdaQueryWrapper);

       return R.success(taskList);
    }

    private StringBuilder buildTask(RandomTask randomTask){
        StringBuilder task = new StringBuilder();
        task.append("Hi, you received a random task!\n");
        task.append("Title: " + randomTask.getTitle() + "\n");
        task.append("Description:"+randomTask.getDescription()+"\n");
        if(randomTask.getSchedule() == 0){
            task.append("Schedule: daily");
        }else if(randomTask.getSchedule() == 1){
            task.append("Schedule: weekly");
        }else{
            task.append("Schedule: monthly");
        }
        return task;
    }





}
