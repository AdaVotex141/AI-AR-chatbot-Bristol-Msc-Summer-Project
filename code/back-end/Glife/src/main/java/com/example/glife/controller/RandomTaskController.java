package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.entity.RandomTask;
import com.example.glife.entity.SystemRoutine;
import com.example.glife.service.SystemRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/random")
public class RandomTaskController {


    @Autowired
    SystemRoutineService systemRoutineService;

    @PostMapping("/add")
    public R<String> addFromRandomTask(HttpServletRequest request, @RequestBody RandomTask randomTask){
        return systemRoutineService.addFromRandomTask(request, randomTask);
    }


}
