/*
package com.example.glife.controller;

import com.example.glife.common.R;
import com.example.glife.config.CarbonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/webhook")
public class APIController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarbonConfig carbonConfig;

    @PostMapping
    public R<String> handleWebhook(@RequestBody String requestBody){

*/
/*        log.info("Received webhook request: " + webhookRequest);


        WebhookResponse webhookResponse = new WebhookResponse();
        webhookResponse.setOutput(new Output("text", "Hello! This is the response from the webhook."));

        return webhookResponse;*//*

        return null;
    }

    @PostMapping
    public R<String> CarbonEmissionAPI(@RequestBody String requestBody){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + carbonConfig.getCarbonApiKey());

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(carbonConfig.getApiUrl(), HttpMethod.POST, entity, String.class);

        //return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        return null;
    }



}
*/
