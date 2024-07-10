package com.example.glife.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.glife.common.R;
import com.example.glife.entity.Response;
import com.example.glife.service.AssistantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/assistant")
public class AssistantController {
    @Autowired
    private AssistantService assistantService;

    @PostMapping ("/input")
    public R<Response> assistantInput(HttpServletRequest request,@RequestBody String inputMessage){
        JSONObject jsonObject = JSONUtil.parseObj(inputMessage);
        String extractedValue = jsonObject.getStr("inputMessage");
        if(assistantService.getSessionId() == null){
            return R.error("the sessionId is null");
        }
        return R.success(assistantService.sendMessage(extractedValue,request));
    }

}
