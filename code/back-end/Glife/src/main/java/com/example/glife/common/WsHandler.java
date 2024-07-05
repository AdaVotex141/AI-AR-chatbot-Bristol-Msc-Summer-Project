package com.example.glife.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


@Component
@Slf4j
public class WsHandler extends AbstractWebSocketHandler {
    private static Map<String,SessionBean> sessionBeanMap;
    private static AtomicInteger clientID;
    static{
        sessionBeanMap=new ConcurrentHashMap<>();
        clientID=new AtomicInteger(0);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        SessionBean sessionBean=new SessionBean(session,clientID.getAndIncrement());
        sessionBeanMap.put(session.getId(),sessionBean);
        log.info(sessionBeanMap.get(session.getId()).getID()+":"+"connect");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info(sessionBeanMap.get(session.getId()).getID()+":"+message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        if(session.isOpen()){
            session.close();
        }
        sessionBeanMap.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info(sessionBeanMap.get(session.getId()).getID()+":"+"close");
    }

}
