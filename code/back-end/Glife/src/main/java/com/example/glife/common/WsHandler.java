package com.example.glife.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.glife.entity.Marker;
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
    private static Map<String, SessionBean> sessionBeanMap;
    private static AtomicInteger clientID;

    private static Map<String,Boolean> setMap;
    private static Map<String,Double> latitudeMap;
    private static Map<String,Double> longitudeMap;

    @Autowired
    LocationServiceImp locationServiceImp;


    @Autowired
    UserService userService;


    static {
        sessionBeanMap = new ConcurrentHashMap<>();
        setMap= new ConcurrentHashMap<>();
        latitudeMap=new ConcurrentHashMap<>();
        longitudeMap=new ConcurrentHashMap<>();
        clientID = new AtomicInteger(0);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        SessionBean sessionBean = new SessionBean(session, clientID.getAndIncrement());
        sessionBeanMap.put(session.getId(), sessionBean);
        setMap.put(session.getId(),false);
        log.info(sessionBeanMap.get(session.getId()).getID() + ":" + "connect");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info(sessionBeanMap.get(session.getId()).getID() + ":" + message.getPayload());


        JSONObject jsonObject = JSONUtil.parseObj(message.getPayload());
        JSONObject userNameObj = jsonObject.getJSONObject("userName");

        String name = userNameObj != null ? userNameObj.getStr("_value") : null;
        log.info("name is ------:{}", name);
        //Long userID = userService.getUserID(name);
        if (!session.getAttributes().containsKey(name)) {
            //session.getAttributes().put("userID", userID);
            session.getAttributes().put("name", name);
        }

        handleMessageType(session, message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        if (session.isOpen()) {
            session.close();
        }
        sessionBeanMap.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info(sessionBeanMap.get(session.getId()).getID() + ":" + "close");
    }


    private void handleMessageType(WebSocketSession session, String message) {
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

    private void handleCurrentLocation(WebSocketSession session, JSONObject jsonObject) {
        if (session != null) {
            JSONObject LongObj = jsonObject.getJSONObject("longitude");
            JSONObject LatitudeObj = jsonObject.getJSONObject("latitude");
            double longitude = LongObj.getDouble("_value");
            double latitude = LatitudeObj.getDouble("_value");
            if(!setMap.get(session.getId())) {
                sendLocation(session, jsonObject);
                setMap.remove(session.getId());
                setMap.put(session.getId(), true);
                latitudeMap.put(session.getId(), latitude);
                longitudeMap.put(session.getId(), longitude);
            }else {
                if(arePointsNear(latitudeMap.get(session.getId()),longitudeMap.get(session.getId()),latitude,longitude)){
                    sendLocation(session, jsonObject);
                    latitudeMap.remove(session.getId());
                    latitudeMap.put(session.getId(), latitude);
                    longitudeMap.remove(session.getId());
                    longitudeMap.put(session.getId(), longitude);
                }
            }
        }
    }

    private Boolean arePointsNear(Double latitudeSession,Double longitudeSession,Double latitude,Double longitude){
        double dLat = Math.toRadians(latitudeSession-latitude);
        double dLon = Math.toRadians(longitudeSession-longitude);
        double latitude1=Math.toRadians(latitude);
        double latitude2=Math.toRadians(latitudeSession);
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(latitude1) *
                        Math.cos(latitude2);
        double rad = 6371000;
        double c = 2 * Math.asin(Math.sqrt(a));
        return (rad * c > 10);
    }

    private void sendLocation(WebSocketSession session, JSONObject jsonObject) {
        // 从 JSON 对象中提取经度和纬度
        JSONObject longitudeObj = jsonObject.getJSONObject("longitude");
        JSONObject latitudeObj = jsonObject.getJSONObject("latitude");
        double longitude = longitudeObj.getDouble("_value");
        double latitude = latitudeObj.getDouble("_value");

        List<Marker> markers = locationServiceImp.getNearByPosition(longitude, latitude).getData();

        if (markers != null && !markers.isEmpty()) {
            for (Marker marker : markers) {
                if (marker != null) {
                    Point point = marker.getPoint();
                    String userName = marker.getName();

                    double x = point.getX();
                    double y = point.getY();

                    log.info("User: " + userName + " - X: " + x + ", Y: " + y);

                    try {
                        String message = String.format("User: %s, Location: %.6f, %.6f", userName, x, y);
                        session.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        log.error("Error sending WebSocket message", e);
                    }
                }
            }
        } else {
            log.info("No nearby markers found");
        }
    }

    private void handlePlantLocation(WebSocketSession session, JSONObject jsonObject) {
        JSONObject LongObj = jsonObject.getJSONObject("longitude");
        JSONObject LatitudeObj = jsonObject.getJSONObject("latitude");
        double longitude = LongObj.getDouble("_value");
        double latitude = LatitudeObj.getDouble("_value");
        locationServiceImp.store(session, longitude, latitude);
        for(String key:sessionBeanMap.keySet()){
            double longitudeLocation=longitudeMap.get(key);
            double latitudeLocation=latitudeMap.get(key);
            if(!arePointsNear(latitudeLocation,longitudeLocation,latitude,longitude)){
                try {
                    sessionBeanMap.get(key).getWebSocketSession().sendMessage(new TextMessage(longitude+","+latitude));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        log.info(longitude + "" + latitude);

    }


    private void handleMSG(WebSocketSession session, JSONObject jsonObject) {

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
