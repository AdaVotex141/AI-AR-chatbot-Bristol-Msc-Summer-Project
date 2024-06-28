package com.example.glife.controller;

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
        return routineService.add(request,content);
    }

    @PostMapping("/update")
    public R<Routine> update(HttpServletRequest request, @RequestBody Routine routine){
        return routineService.update(request,routine);
    }

    @PostMapping("/tick")
    public R<Routine> tick(HttpServletRequest request, @RequestBody Long id){
        return routineService.tick(request,id);
    }

    @PostMapping("/delete")
    public R<String> delete(HttpServletRequest request, @RequestBody Long id){
        return routineService.delete(request,id);
    }

}
