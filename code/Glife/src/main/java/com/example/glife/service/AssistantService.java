package com.example.glife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.glife.config.AssistantConfig;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.MessageResponse;

public interface AssistantService {
    void initializeAssistant();
    Assistant getAssistant();
    void createSession();
    void closeSession();
    MessageResponse sendMessage(String inputMessage);
}
