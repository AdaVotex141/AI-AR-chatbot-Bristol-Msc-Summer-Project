package com.example.glife;

import com.example.glife.config.AssistantConfig;
import com.example.glife.service.AssistantService;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.RequestTooLargeException;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.watson.assistant.v1.model.MessageResponse;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.RuntimeResponseGeneric;
import com.ibm.watson.assistant.v2.model.StatefulMessageResponse;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssistantAPITest {
    @Autowired
    private AssistantService assistantService;
    private String sessionId;

    @BeforeEach
    void setUp() {
        //assistantService.initializeAssistant();
        //assistantService.createSession();
        //sessionId = assistantService.getSessionId();
    }

    @Test
    void getVersion(){


    }
/*    @Test
    void helloAssistant() {

        try {
            MessageInput input = new MessageInput.Builder()
                    .messageType("text")
                    .text("what do you do")
                    .build();

            MessageOptions options = new MessageOptions.Builder(assistantService.getAssistantId(), sessionId)
                    .input(input)
                    .build();

            StatefulMessageResponse response = assistantService.getAssistant().message(options).execute().getResult();

            System.out.println(response.toString());
            // Invoke a method
        } catch (RequestTooLargeException e) {
            // Handle Request Too Large (413) exception
        } catch (ServiceResponseException e) {
            // Base class for all exceptions caused by error responses from the service
            System.out.println("Service returned status code "
                    + e.getStatusCode() + ": " + e.getMessage());
        }

    }*/
}
