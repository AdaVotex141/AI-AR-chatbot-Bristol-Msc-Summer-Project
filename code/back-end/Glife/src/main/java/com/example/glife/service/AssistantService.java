package com.example.glife.service;

import com.example.glife.entity.Response;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.StatefulMessageResponse;

public interface AssistantService {
    void initializeAssistant();
    Assistant getAssistant();
    void createSession();
    void closeSession();
    String getSessionId();
    public Response sendMessage(String inputMessage);
}
