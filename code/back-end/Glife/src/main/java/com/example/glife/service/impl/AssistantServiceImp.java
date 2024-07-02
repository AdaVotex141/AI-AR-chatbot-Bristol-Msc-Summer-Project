package com.example.glife.service.impl;

import com.example.glife.config.AssistantConfig;
import com.example.glife.entity.Response;
import com.example.glife.entity.ResponseSection;
import com.example.glife.service.AssistantService;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AssistantServiceImp implements AssistantService {

    private Assistant assistant;
    private String sessionId;

    @Autowired
    private AssistantConfig assistantConfig = new AssistantConfig();


    public void initializeAssistant() {
        IamAuthenticator authenticator = new IamAuthenticator(assistantConfig.getApikey());
        assistant = new Assistant(assistantConfig.getVersion(), authenticator);
        assistant.setServiceUrl(assistantConfig.getServiceUrl());

        createSession();
    }

    public Assistant getAssistant() {
        return assistant;
    }
    private void setAssistant(Assistant assistant){
        this.assistant = assistant;
    }


    public void createSession() {
        CreateSessionOptions options = new CreateSessionOptions.Builder(assistantConfig.getEnvironmentID()).build();
        SessionResponse response = assistant.createSession(options).execute().getResult();
        sessionId = response.getSessionId();

        log.info("----------Session ID: {}-----------", sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }


    public void closeSession() {
        if (sessionId != null) {
            assistant.deleteSession(new DeleteSessionOptions.Builder(assistantConfig.getEnvironmentID(), sessionId).build()).execute();
            sessionId = null;
            log.info("Session closed successfully.");
        }
    }

    public Response sendMessage(String inputMessage) {
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text(inputMessage)
                .build();

        MessageOptions options = new MessageOptions.Builder(assistantConfig.getEnvironmentID(), this.sessionId)
                .input(input)
                .build();
        StatefulMessageResponse statefulMessageResponse = assistant.message(options).execute().getResult();
        Response response = setResponse(statefulMessageResponse);
        return response;
    }

    //text options action?
    public Response setResponse(StatefulMessageResponse responseMessage){
        List<RuntimeResponseGeneric> generics = responseMessage.getOutput().getGeneric();
        Response response = new Response();
        for(RuntimeResponseGeneric generic:generics){
            switch (generic.responseType()){
                case "text":
                    response.getResponseSectionList().add(setText(generic));
                    break;
                case "option":
                    response.getResponseSectionList().add(setOptions(generic));
                    break;
                default:
                    response.setError("Can't resolve response type");
                    break;
            }
        }
        return response;
    }

    public ResponseSection setText(RuntimeResponseGeneric generic){
        ResponseSection section = new ResponseSection();
        section.setResponseType(generic.responseType());
        section.setText(generic.text());
        return section;
    }

    public ResponseSection setOptions(RuntimeResponseGeneric generic){
        ResponseSection section = new ResponseSection();
        section.setResponseType(generic.responseType());
        List<DialogNodeOutputOptionsElement> options = generic.options();
        for(DialogNodeOutputOptionsElement option:options){
            String label = option.getLabel();
            section.getLabels().add(label);
        }

        return section;
    }


    // for test only
    public static void main(String[] args) {
        //test init
        IamAuthenticator authenticator = new IamAuthenticator("S4m5c3m1vdnSy0WKlEHwbTndaAxqZBKcKiDDTAIKbuO5");
        Assistant assistant = new Assistant("2023-06-15", authenticator);
        assistant.setServiceUrl("https://api.eu-gb.assistant.watson.cloud.ibm.com");

        // create session
        CreateSessionOptions createSessionOptions = new CreateSessionOptions
                .Builder("f450d030-5b09-4c55-94b3-59f66c4088cb")
                .build();
        SessionResponse sessionResponse = assistant.createSession(createSessionOptions).execute().getResult();
        String sessionId = sessionResponse.getSessionId();
        System.out.print("sessionID:"+sessionId+"\n");

        //test input
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text("suggestion food")
                .build();

        MessageOptions options = new MessageOptions.Builder("f450d030-5b09-4c55-94b3-59f66c4088cb", sessionId)
                .input(input)
                .build();
        StatefulMessageResponse response = assistant.message(options).execute().getResult();
        System.out.print("response"+response);

        //close session
     /*   if (sessionId != null) {
            assistant.deleteSession(new DeleteSessionOptions.Builder("f450d030-5b09-4c55-94b3-59f66c4088cb", sessionId).build()).execute();
            sessionId = null;
            //log.info("Session closed successfully.");
        }*/


    }
}
