package com.example.glife.common;

import cn.hutool.json.JSONUtil;
import com.example.glife.entity.RandomTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.glife.common.RedisConstants.*;

@Component
@Slf4j
public class MessageWsHandler extends TextWebSocketHandler {

    //<userID, session>
    private static ConcurrentMap<Long, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    //User online
    @Autowired
    StringRedisTemplate template;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Get userID from session URI
        String userIdStr = getUserIdFromSession(session);
        if (userIdStr != null) {
            try {
                //set UserID
                Long userID = Long.parseLong(userIdStr);
                session.getAttributes().put("userID", userID);
                userSessions.put(userID, session);
                template.opsForSet().remove(USER_OFFLINE,userID.toString());
                template.opsForSet().add(USER_ONLINE,userID.toString());
                //get tasklist before user login
                List<String> taskList = template.opsForList().range(USER_MESSAGES + userID, 0, -1);
                if (taskList != null && !taskList.isEmpty()) {
                    for (String task : taskList) {
                        sendTaskToOneUser(session, task);
                    }
                    template.delete(USER_MESSAGES + userID);
                }
            } catch (NumberFormatException e) {
                session.close();
                throw new IllegalArgumentException("Invalid user ID format", e);
            }
        } else {
            session.close();
            throw new IllegalStateException("User ID not found in session URI");
        }
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
        template.opsForSet().add(USER_OFFLINE,userID.toString());
        template.opsForSet().remove(USER_ONLINE,userID.toString());

        log.info("user disconnected:{}", userID);
    }

    private void broadCast(String task){
        //broadCast all the user online
        for (Map.Entry<Long, WebSocketSession> entry : userSessions.entrySet()){
            WebSocketSession session = entry.getValue();
            if(session.isOpen()){
                try{
                    session.sendMessage(new TextMessage(task));
                } catch (IOException e) {
                    log.error("Failed to send message to user: {}", entry.getKey(), e);
                }
            }
        }

        //Deal with user not online
        Set<String> offlineUsers = template.opsForSet().members(USER_OFFLINE);
        if (offlineUsers != null && !offlineUsers.isEmpty()) {
            for (String userID : offlineUsers) {
                template.opsForList().leftPush(USER_MESSAGES + userID, task);
            }
        }
    }


    /**
     * this is one to one message
     * @param session
     * @param task
     */
    private void sendTaskToOneUser(WebSocketSession session, String task) {
        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(task));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getUserIdFromSession(WebSocketSession session) {
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
