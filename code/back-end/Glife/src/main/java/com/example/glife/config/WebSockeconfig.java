package com.example.glife.config;

import com.example.glife.common.ARtreeInterceptor;
import com.example.glife.common.MessageInterceptor;
import com.example.glife.common.MessageWsHandler;
import com.example.glife.common.WsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class WebSockeconfig implements WebSocketConfigurer {
    @Resource
    WsHandler wsHandler;
    @Resource
    ARtreeInterceptor ARtreeInterceptor;
    @Resource
    MessageWsHandler messageWsHandler;
    @Resource
    MessageInterceptor messageInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler,"/ARtree").addInterceptors(ARtreeInterceptor).setAllowedOrigins("*");
        registry.addHandler(messageWsHandler,"/message").addInterceptors(messageInterceptor).setAllowedOrigins("*");
    }
}//webo/message&userId=238908290348093289023
