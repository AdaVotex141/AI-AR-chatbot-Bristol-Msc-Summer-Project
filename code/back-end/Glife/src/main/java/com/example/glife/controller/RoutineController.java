package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.entity.Routine;
import com.example.glife.service.RoutineService;
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
@RequestMapping("/api/routine")
public class RoutineController {
    @Autowired
    private RoutineService routineService;

    public R<List<Routine>> init(HttpServletRequest request){
        return routineService.init(request);
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody Routine routine){
        return routineService.add(request,routine);
    }

    @PostMapping("/update")
    public R<Routine> update(HttpServletRequest request, @RequestBody Routine routine){
        return routineService.update(request,routine);
    }

    @PostMapping("/tick")
    public R<Routine> tick(HttpServletRequest request, @RequestBody Routine routine){
        return routineService.tick(request,routine);
    }

    @PostMapping("/delete")
    public R<String> delete(HttpServletRequest request, Routine routine){
        return routineService.delete(request,routine);
    }


}
