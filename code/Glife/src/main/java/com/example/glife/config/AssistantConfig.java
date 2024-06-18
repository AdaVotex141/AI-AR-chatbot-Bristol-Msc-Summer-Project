package com.example.glife.config;

import com.example.glife.GlifeApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "assistant")
public class AssistantConfig {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(GlifeApplication.class, args);
        AssistantConfig config = context.getBean(AssistantConfig.class);

        System.out.println("API Key: " + config.getApikey());
        System.out.println("Version: " + config.getVersion());
        System.out.println("Service URL: " + config.getServiceUrl());
        System.out.println("Assistant ID: " + config.getAssistantId());
        System.out.println("Environment ID: " + config.getEnvironmentID());

    }

    private String apikey;
    private String version;
    private String serviceUrl;
    private String assistantId;
    private String environmentID;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }

    public void setEnvironmentID(String environmentID){
        this.environmentID=environmentID;
    }

    public String getEnvironmentID(){
        return environmentID;
    }


}
