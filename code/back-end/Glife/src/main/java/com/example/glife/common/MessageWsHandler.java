package com.example.glife.common;

import com.example.glife.entity.RandomTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MessageWsHandler extends TextWebSocketHandler {

    //<userID, session>
    private static Map<Long, WebSocketSession> userSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userID = Long.parseLong(getUserIdFromSession(session));
        session.getAttributes().put("userID", userID);
        userSessions.put(userID, session);

        log.info("user connected:{}", userID);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
        String payload = message.getPayload();


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long userID = (Long) session.getAttributes().get("userID");
        if(userID != null){
            userSessions.remove(userID);
        }

        log.info("user disconnected:{}", userID);
    }

    public void broadcast(String message) {
        for (WebSocketSession session : userSessions.values()) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getUserIdFromSession(WebSocketSession session) {
        URI sessionUri = session.getUri();
        if (sessionUri != null) {
            String query = sessionUri.getQuery();
            if (query != null){
                String[] params = query.split("&");
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2 && "userId".equals(keyValue[0])) {
                        return keyValue[1];
                    }
                }
            }
        }

        return null;
    }


}
