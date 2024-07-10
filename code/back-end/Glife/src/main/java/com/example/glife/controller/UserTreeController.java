package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.service.UserTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/ARTree")
public class UserTreeController {
    @Autowired
    UserTreeService service;

    @GetMapping("/init")
    public R<Integer> init(HttpServletRequest request){
        return service.init(request);
    }

}
