package com.example.glife.service.impl;

import com.example.glife.common.R;
import com.example.glife.common.RedisConstants;
import com.example.glife.config.AssistantConfig;
import com.example.glife.entity.Response;
import com.example.glife.entity.ResponseSection;
import com.example.glife.entity.User;
import com.example.glife.service.AssistantService;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AssistantServiceImp implements AssistantService {

    private Assistant assistant;
    private String sessionId;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private AssistantConfig assistantConfig = new AssistantConfig();

    private Stack<String> selections;

    /**
     *
     */
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
        this.selections = new Stack<>();

        log.info("----------Session ID: {}-----------", sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }


    public void closeSession() {
        if (sessionId != null) {
            assistant.deleteSession(new DeleteSessionOptions.Builder(assistantConfig.getEnvironmentID(), sessionId).build()).execute();
            sessionId = null;
            selections.clear();
            log.info("Session closed successfully.");
        }
    }

    /**
     *
     * @param inputMessage
     * @param request
     * @return
     */
    public Response sendMessage(String inputMessage, HttpServletRequest request) {
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text(inputMessage)
                .build();

        MessageOptions options = new MessageOptions.Builder(assistantConfig.getEnvironmentID(), this.sessionId)
                .input(input)
                .build();
        StatefulMessageResponse statefulMessageResponse = assistant.message(options).execute().getResult();
        Response response = setResponse(statefulMessageResponse, request);
        return response;
    }

    //text options action?

    /**
     *
     * @param responseMessage
     * @param request
     * @return
     */
    public Response setResponse(StatefulMessageResponse responseMessage, HttpServletRequest request){
        List<RuntimeResponseGeneric> generics = responseMessage.getOutput().getGeneric();
        Response response = new Response();
        for(RuntimeResponseGeneric generic:generics){
            switch (generic.responseType()){
                case "text":
                    response.getResponseSectionList().add(setText(generic, request));
                    break;
                case "option":
                    response.getResponseSectionList().add(setOptions(generic, request));
                    break;
                default:
                    response.setError("Can't resolve response type");
                    break;
            }
        }
        return response;
    }

    /**
     *
     * @param generic
     * @param request
     * @return
     */
    public ResponseSection setText(RuntimeResponseGeneric generic, HttpServletRequest request){
        ResponseSection section = new ResponseSection();
        section.setResponseType(generic.responseType());
        section.setText(generic.text());
        Long userId = getUserID(request);
        //if there are selection, put it in redis
        if(generic.text().contains("Do you want to") && userId != 0){
            String option = textParser(generic.text());
            String key =  RedisConstants.User_SELECTION+userId;
            stringRedisTemplate.opsForValue().set(key,option,RedisConstants.OPTION_TTL, TimeUnit.MINUTES);
        }
        return section;
    }

    public Stack<String> getSelections(){
        return selections;
    }

    public Long getUserID(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = null;
        Long userid = Long.valueOf(0);
        if(session != null && session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }
        userid = user.getId();
        return userid;
    }

    /**
     *
     * @param generic
     * @param request
     * @return
     */
    public ResponseSection setOptions(RuntimeResponseGeneric generic, HttpServletRequest request){
        ResponseSection section = new ResponseSection();
        section.setResponseType(generic.responseType());
        List<DialogNodeOutputOptionsElement> options = generic.options();
        for(DialogNodeOutputOptionsElement option:options){
            String label = option.getLabel();
            section.getLabels().add(label);
        }

        return section;
    }

    /**
     *
     * @param text
     * @return
     */
    public String textParser(String text){
        String selectedText = "";
        String[] splitText = text.split(":");
        if(splitText[0] != null){
            return splitText[0];
        }
        return "0";
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
