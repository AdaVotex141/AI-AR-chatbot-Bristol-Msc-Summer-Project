package com.example.glife.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.glife.service.UserService;
import com.example.glife.service.impl.LocationServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


@Component
@Slf4j
public class WsHandler extends AbstractWebSocketHandler {
    private static Map<String,SessionBean> sessionBeanMap;
    private static AtomicInteger clientID;

    @Autowired
    LocationServiceImp locationServiceImp;

    @Autowired
    UserService userService;


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
    //jjjjj

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info(sessionBeanMap.get(session.getId()).getID()+":"+message.getPayload());


        JSONObject jsonObject = JSONUtil.parseObj(message.getPayload());
        JSONObject userNameObj = jsonObject.getJSONObject("userName");

        String name = userNameObj != null ? userNameObj.getStr("_value") : null;
        log.info("name is ------:{}", name);
        Long userID = userService.getUserID(name);
        if(!session.getAttributes().containsKey(name)){
            session.getAttributes().put("userID", userID);
        }
        handleMessageType(session,message.getPayload());
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



    private void handleMessageType(WebSocketSession session, String message){
        log.info("______");
        JSONObject jsonObject = JSONUtil.parseObj(message);
        String type = jsonObject.getStr("type");

        switch (type) {
            case "current-location":
                log.info("____");
                handleCurrentLocation(session, jsonObject);
                break;
            case "plant-location":
                handlePlantLocation(session, jsonObject);
                break;
            case "MSG":
                handleMSG(session, jsonObject);
                break;
            default:
                log.warn("Unknown message type: " + type);
        }
    }

    private void handleCurrentLocation(WebSocketSession session, JSONObject jsonObject){
//        HttpServletRequest request = getCurrentHttpRequest();

        if (session != null) {
            JSONObject userLongtitueObj = jsonObject.getJSONObject("longitude");
            JSONObject LattitueObj = jsonObject.getJSONObject("latitude");
            double longitude = userLongtitueObj.getDouble("_value");
            double latitude = LattitueObj.getDouble("_value");


            List<Point> points = locationServiceImp.getNearByPosition(session, longitude, latitude).getData();

            if (points != null && !points.isEmpty()) {
                for (Point point : points) {
                    if (point != null) {
                        double x = point.getX();
                        double y = point.getY();
                        try {
                            session.sendMessage(new TextMessage(x + "," + y));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else {
                points = new ArrayList<>();
            }


        }

    }

    private void handlePlantLocation(WebSocketSession session, JSONObject jsonObject){
//        HttpServletRequest request = getCurrentHttpRequest();

        if (session != null) {
            JSONObject userLongtitueObj = jsonObject.getJSONObject("longitude");
            JSONObject LattitueObj = jsonObject.getJSONObject("latitude");
            double longitude = userLongtitueObj.getDouble("_value");
            double latitude = LattitueObj.getDouble("_value");

            locationServiceImp.store(session, longitude, latitude);
            log.info("long:"+longitude+"latitude"+latitude);
        }

    }





    private void handleMSG(WebSocketSession session, JSONObject jsonObject){

    }



    /**
     * this is for getting session in the request
     * @return
     */
//    private HttpServletRequest getCurrentHttpRequest() {
//        ServletRequestAttributes attrs =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        return (attrs != null) ? attrs.getRequest() : null;
//    }




}
