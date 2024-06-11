package com.example.glife.service.impl;

import com.example.glife.config.AssistantConfig;
import com.example.glife.service.AssistantService;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope("prototype")
@Slf4j
public class AssistantServiceImp implements AssistantService{

    private Assistant assistant;
    private String sessionId;

    @Autowired
    private AssistantConfig assistantConfig;


    public void initializeAssistant() {
        IamAuthenticator authenticator = new IamAuthenticator(assistantConfig.getApikey());
        assistant = new Assistant(assistantConfig.getVersion(), authenticator);
        assistant.setServiceUrl(assistantConfig.getServiceUrl());
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void createSession(){
        CreateSessionOptions options = new CreateSessionOptions.Builder(assistantConfig.getAssistantId()).build();
        SessionResponse response = assistant.createSession(options).execute().getResult();
        sessionId = response.getSessionId();
        log.info("Session ID: {}" ,sessionId);

    }

    public void closeSession() {
        if (sessionId != null) {
            assistant.deleteSession(new DeleteSessionOptions.Builder(assistantConfig.getAssistantId(), sessionId).build()).execute();
            sessionId = null;
            log.info("Session closed successfully.");
        }
    }

    public MessageResponse sendMessage(String inputMessage) {
        MessageInput input = new MessageInput.Builder()
                .text(inputMessage)
                .build();

        MessageOptions options = new MessageOptions.Builder(assistantConfig.getAssistantId(),sessionId)
                .input(input)
                .build();

        return assistant.message(options).execute().getResult();
    }
}
