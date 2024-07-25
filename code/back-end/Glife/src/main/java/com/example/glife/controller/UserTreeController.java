package com.example.glife.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.glife.common.R;
import com.example.glife.entity.User;
import com.example.glife.entity.UserTree;
import com.example.glife.mapper.UserTreeMapper;
import com.example.glife.service.UserTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/ARTree")
public class UserTreeController {
    @Autowired
    UserTreeService service;

    @Autowired
    UserTreeMapper mapper;

    @GetMapping("/init")
    public R<Integer> init(HttpServletRequest request){
        int getData = service.init(request).getData();
        log.info("the get Data is :{}",getData);
        return service.init(request);
    }

    @GetMapping("/addTreeSum")
    public R<String> addTreeSum(HttpServletRequest request){
        return service.plant(request);
    }

    @GetMapping("/getTreeSum")
    public R<Integer> getTreeSum(HttpServletRequest request){
        User currentUser = (User)  request.getSession(false).getAttribute("user");
        Long userId = currentUser.getId();

        LambdaQueryWrapper<UserTree> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserTree::getUserid, userId);
        UserTree userTree = mapper.selectOne(lambdaQueryWrapper);
        if(userTree == null){
            return R.success(0);
        }else{
            return R.success(userTree.getTreeSum());
        }
    }

}
