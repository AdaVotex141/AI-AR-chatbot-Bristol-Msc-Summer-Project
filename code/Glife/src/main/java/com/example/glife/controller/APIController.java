package com.example.glife.controller;

import com.example.glife.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/webhook")
public class APIController {

    @PostMapping
    public R<String> handleWebhook(@RequestBody String requestBody){

/*        log.info("Received webhook request: " + webhookRequest);


        WebhookResponse webhookResponse = new WebhookResponse();
        webhookResponse.setOutput(new Output("text", "Hello! This is the response from the webhook."));

        return webhookResponse;*/
        return null;
    }
}
