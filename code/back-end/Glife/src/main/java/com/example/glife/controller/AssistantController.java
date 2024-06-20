package com.example.glife.controller;

import com.example.glife.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssistantController {
    @Autowired
    private AssistantService assistantService;
}
