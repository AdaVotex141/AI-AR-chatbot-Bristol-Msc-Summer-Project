package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.entity.Response;
import com.example.glife.service.AssistantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/assistant")
public class AssistantController {
    @Autowired
    private AssistantService assistantService;

    @PostMapping ("/input")
    public R<Response> assistantInput(String inputMessage){
        return R.success(assistantService.sendMessage(inputMessage));
    }

}
