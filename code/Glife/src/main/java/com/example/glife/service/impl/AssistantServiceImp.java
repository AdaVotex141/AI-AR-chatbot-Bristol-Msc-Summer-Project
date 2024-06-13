package com.example.glife.service.impl;

import com.example.glife.config.AssistantConfig;
import com.example.glife.service.AssistantService;
import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v1.model.MessageResponse;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Slf4j
public class AssistantServiceImp implements AssistantService {

    private Assistant assistant;
    private String sessionId;
    private String assistantId;

    @Autowired
    private AssistantConfig assistantConfig;


    public void initializeAssistant() {
        IamAuthenticator authenticator = new IamAuthenticator(assistantConfig.getApikey());
        assistant = new Assistant(assistantConfig.getVersion(), authenticator);
        assistant.setServiceUrl(assistantConfig.getServiceUrl());
        assistantId = assistantConfig.getAssistantId();
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public void createSession() {
        CreateSessionOptions options = new CreateSessionOptions.Builder(assistantConfig.getAssistantId()).build();
        SessionResponse response = assistant.createSession(options).execute().getResult();
        sessionId = response.getSessionId();
        log.info("Session ID: {}", sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void closeSession() {
        if (sessionId != null) {
            assistant.deleteSession(new DeleteSessionOptions.Builder(assistantConfig.getAssistantId(), sessionId).build()).execute();
            sessionId = null;
            log.info("Session closed successfully.");
        }
    }

    public StatefulMessageResponse sendMessage(String inputMessage) {
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text(inputMessage)
                .build();

        MessageOptions options = new MessageOptions.Builder(assistantId, sessionId)
                .input(input)
                .build();

        return assistant.message(options).execute().getResult();
    }


    public static void main(String[] args) {
        IamAuthenticator authenticator = new IamAuthenticator("S4m5c3m1vdnSy0WKlEHwbTndaAxqZBKcKiDDTAIKbuO5");
        Assistant assistant = new Assistant("2023-06-15", authenticator);
        assistant.setServiceUrl("https://api.eu-gb.assistant.watson.cloud.ibm.com");

        CreateSessionOptions createSessionOptions = new CreateSessionOptions.Builder("d5404082-d252-4ecc-9df0-ece0170f0970").build();
        SessionResponse sessionResponse = assistant.createSession(createSessionOptions).execute().getResult();
        String sessionId = sessionResponse.getSessionId();
        System.out.print(sessionId);


//        service.createSession();
////        String sessionId = service.getSessionId();
//        MessageInput input = new MessageInput.Builder()
//                .messageType("text")
//                .text("Hello")
//                .build();
//
//        MessageOptions options = new MessageOptions.Builder("d5404082-d252-4ecc-9df0-ece0170f0970",sessionId)
//                .input(input)
//                .build();
//
//        StatefulMessageResponse response = assistant.message(options).execute().getResult();
//        System.out.println(response);
    }
}
