package com.example.glife.service;

import com.example.glife.entity.Response;
import com.example.glife.entity.ResponseSection;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.RuntimeResponseGeneric;
import com.ibm.watson.assistant.v2.model.StatefulMessageResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Stack;

public interface AssistantService {
    void initializeAssistant();
    Assistant getAssistant();
    void createSession();
    void closeSession();
    String getSessionId();
    public Response sendMessage(String inputMessage, HttpServletRequest request);
    public Response setResponse(StatefulMessageResponse responseMessage, HttpServletRequest request);
    public ResponseSection setText(RuntimeResponseGeneric generic, HttpServletRequest request);
    public ResponseSection setOptions(RuntimeResponseGeneric generic, HttpServletRequest request);
    public String textParser(String text);
    public Long getUserID(HttpServletRequest request);
    public Stack<String> getSelections();

}
