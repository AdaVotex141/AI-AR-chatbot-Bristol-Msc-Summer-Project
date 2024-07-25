package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.entity.Statistics;
import com.example.glife.service.impl.StatisticsServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/stat")
public class StatController {

    @Autowired
    StatisticsServiceImp serviceImp;

    @GetMapping
    public R<Statistics> init(HttpServletRequest request){
        return serviceImp.init(request);
    }
}
