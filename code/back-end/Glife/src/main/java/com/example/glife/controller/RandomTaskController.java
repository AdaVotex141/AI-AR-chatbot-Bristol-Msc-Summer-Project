package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.entity.RandomTask;
import com.example.glife.entity.SystemRoutine;
import com.example.glife.service.RandomTaskService;
import com.example.glife.service.SystemRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/randomTask")
public class RandomTaskController {


    @Autowired
    RandomTaskService randomTaskService;

    @PostMapping("/init")
    public R<List<RandomTask>> init(HttpServletRequest request){
       return randomTaskService.init(request);
    }

    @PostMapping("/send")
    public R<String> send(HttpServletRequest request, RandomTask randomTask){
        return randomTaskService.send(request, randomTask);
    }



}
