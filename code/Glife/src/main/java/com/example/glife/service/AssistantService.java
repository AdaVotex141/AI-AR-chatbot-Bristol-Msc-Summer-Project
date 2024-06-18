package com.example.glife.service;

import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.StatefulMessageResponse;

public interface AssistantService {
    void initializeAssistant();
    void setAssistant(Assistant assistant);

    Assistant getAssistant();
    void createSession();
    void closeSession();
    String getSessionId();
    String getAssistantId();
    StatefulMessageResponse sendMessage(String inputMessage);
}
