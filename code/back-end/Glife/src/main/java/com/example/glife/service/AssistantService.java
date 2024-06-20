package com.example.glife.service;

import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.StatefulMessageResponse;

public interface AssistantService {
    void initializeAssistant();
    Assistant getAssistant();
    void createSession();
    void closeSession();
    String getSessionId();
    StatefulMessageResponse sendMessage(String inputMessage);
}
