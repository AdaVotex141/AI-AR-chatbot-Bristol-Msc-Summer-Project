package com.example.glife.controller;

import com.example.glife.common.MessageWsHandler;
import com.example.glife.common.R;
import com.example.glife.entity.Routine;
import com.example.glife.entity.SystemRoutine;
import com.example.glife.entity.User;
import com.example.glife.service.RandomTaskReceiverService;
import com.example.glife.service.SystemRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import static com.example.glife.common.RedisConstants.USER_MESSAGES;

@RestController
@Slf4j
@RequestMapping("/randomTask")
public class RandomTaskReceiverController {
    @Autowired
    RandomTaskReceiverService randomTaskReceiverService;

    @GetMapping("/init")
    public R<String> init(HttpServletRequest request){
        return randomTaskReceiverService.init(request);

    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody String task){
        return randomTaskReceiverService.add(request, task);
    }

    @PostMapping("/length")
    public R<Long> MessageQueueLength(HttpServletRequest request){
        return randomTaskReceiverService.MessageQueueLength(request);
    }








}
