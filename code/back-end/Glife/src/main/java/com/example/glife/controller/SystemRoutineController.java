package com.example.glife.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.glife.common.R;
import com.example.glife.entity.Routine;
import com.example.glife.entity.SystemRoutine;
import com.example.glife.service.RoutineService;
import com.example.glife.service.SystemRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/system_routine")
public class SystemRoutineController {

    @Autowired
    private SystemRoutineService routineService;

    @GetMapping("/init")
    public R<List<SystemRoutine>> init(HttpServletRequest request){
        return routineService.init(request);
    }

    @PostMapping("/tick")
    public R<SystemRoutine> tick(HttpServletRequest request, @RequestBody Long id){
        return routineService.tick(request,id);
    }

    @PostMapping("/delete")
    public R<String> delete(HttpServletRequest request, @RequestBody Long id){
        return routineService.delete(request,id);
    }

    @PostMapping("/add-assistant")
    public R<String> addFromAssistant(HttpServletRequest request){
        return routineService.addFromAssistant(request);
    }


    @PostMapping("/add-task")
    public R<String> addFromRandomTask(HttpServletRequest request, String content){
        //TODO
        return null;
    }


}
