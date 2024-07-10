package com.example.glife.common;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

@Setter
@Getter
public class SessionBean {
    private WebSocketSession webSocketSession;
    private Integer ID;

    public SessionBean(WebSocketSession webSocketSession, Integer ID) {
        this.webSocketSession = webSocketSession;
        this.ID = ID;
    }

}
