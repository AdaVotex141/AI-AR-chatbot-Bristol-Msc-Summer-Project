package com.example.glife.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.glife.common.R;
import com.example.glife.entity.Routine;
import com.example.glife.service.RoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/routine")
public class RoutineController {
    @Autowired
    private RoutineService routineService;

    @GetMapping("/init")
    public R<List<Routine>> init(HttpServletRequest request){
        return routineService.init(request);
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody String content){
        JSONObject jsonObject = JSONUtil.parseObj(content);
        String extractedValue = jsonObject.getStr("content");
        return routineService.add(request,extractedValue);
    }

    @PostMapping("/update")
    public R<Routine> update(HttpServletRequest request, @RequestBody Routine routine){
        return routineService.update(request,routine);
    }

    @PostMapping("/tick")
    public R<Routine> tick(HttpServletRequest request, @RequestBody Long id){
        JSONObject jsonObject = JSONUtil.parseObj(id);
        Long extractedValue = jsonObject.getLong("id");
        return routineService.tick(request,extractedValue);
    }

    @PostMapping("/delete")
    public R<String> delete(HttpServletRequest request, @RequestBody Long id){
        JSONObject jsonObject = JSONUtil.parseObj(id);
        Long extractedValue = jsonObject.getLong("id");
        return routineService.delete(request,extractedValue);
    }

}
