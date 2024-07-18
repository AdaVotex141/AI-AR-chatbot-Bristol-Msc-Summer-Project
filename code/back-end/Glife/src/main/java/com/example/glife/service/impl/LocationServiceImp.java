package com.example.glife.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.glife.common.R;
import com.example.glife.common.RedisConstants;
import com.example.glife.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

import static com.example.glife.common.RedisConstants.LOCATION_KEY;
import static com.example.glife.common.RedisConstants.RADIUS;

@Service
public class LocationServiceImp {

    @Autowired
    StringRedisTemplate template;

//    @Autowired
//    RedisTemplate<String, Point> redisTemplate;

    /**
     *
     * @param session
     * @param latitude
     * @param longitude
     * @return
     */
    public R<String> store(WebSocketSession session, double longitude, double latitude) {
        Long userID = (Long) session.getAttributes().get("userID");

        if (userID != null && StrUtil.isNotBlank(userID.toString())) {
            String key = LOCATION_KEY;
            Point point = new Point(longitude, latitude);
            String shortUUID = UUID.randomUUID().toString().substring(0, 8);
            String locationUniqueID = shortUUID;
            Point locationUniqueIDPoint = new Point(longitude, latitude);

            // Store in opsForGeo()
            template.opsForGeo().add(key, point, locationUniqueID);

            //TODO: add treeSum

            return R.success("Location stored successfully");
        } else {
            return R.error("Can't find user");
        }
    }

    public R<List<Point>> getNearByPosition(WebSocketSession session, double longitude, double latitude){
        Point currentLocation = new Point(longitude, latitude);
        Distance radius = new Distance(RADIUS, org.springframework.data.geo.Metrics.MILES);
        String key = LOCATION_KEY;

        // Construct Circle object for the radius query
        Circle circle = new Circle(currentLocation, radius);

//        // Perform radius query
//        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults =
//                template.opsForGeo().radius(key, circle);
//
        List<Point> points = new ArrayList<>();
//        for (GeoResult<RedisGeoCommands.GeoLocation<Point>> geoResult : geoResults) {
//            Point point = geoResult.getContent().getPoint();
//            points.add(point);
//        }

        return R.success(points);
    }

//    public R<Map<Point, Long>> getNearByPostionByUser(HttpServletRequest request, double longitude, double latitude){
//        Point currentLocation = new Point(longitude, latitude);
//        Distance radius = new Distance(RADIUS, RedisGeoCommands.DistanceUnit.METERS);
//        String key = LOCATION_KEY;
//
//        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults =
//                template.opsForGeo().radius(key, String.valueOf(currentLocation), radius);
//
//        Map<Point, Long> pointsMap = new HashMap<>();
//        for (GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult : geoResults) {
//            Point point = geoResult.getContent().getPoint();
//            String locationID = geoResult.getContent().getName();
//
//            String[] IDs = locationID.split("-");
//            Long userID = Long.parseLong(IDs[0]);
//
//            pointsMap.put(point,userID);
//        }
//
//        return R.success(pointsMap);
//    }

//    public R<Map<Long, List<Point>>> getNearByPostionByUser(HttpServletRequest request, double longitude, double latitude){
//        Point currentLocation = new Point(longitude, latitude);
//        Distance radius = new Distance(RADIUS, RedisGeoCommands.DistanceUnit.METERS);
//
//        Map<Long, List<Point>> nearbyTrees = new HashMap<>();
//
//        Set<String> keys = template.keys( LOCATION_KEY+ "*");
//
//
//        for (String key : keys) {
//            long userId = Long.parseLong(key.substring(LOCATION_KEY.length()));
//
//            List<Point> treeLocations = template.opsForGeo().radius(key, String.valueOf(currentLocation), radius).getContent().stream()
//                    .map(GeoResult::getContent)
//                    .map(RedisGeoCommands.GeoLocation::getPoint)
//                    .toList();
//            nearbyTrees.put(userId, treeLocations);
//        }
//
//        return R.success(nearbyTrees);
//    }
//
//    public R<List<Point>> getNearByPosition(HttpServletRequest request, double longitude, double latitude){
//        Point currentLocation = new Point(longitude, latitude);
//        Distance radius = new Distance(RADIUS, RedisGeoCommands.DistanceUnit.METERS);
//
//        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResults =
//                template.opsForGeo().radius(LOCATION_KEY_PREFIX + "*", currentLocation, radius).getContent();
//
//        List<Point> nearbyPoints = geoResults.stream()
//                .map(GeoResult::getContent)
//                .map(RedisGeoCommands.GeoLocation::getPoint)
//                .toList();
//
//        return R.success(nearbyPoints);
//
//    }



//    private Long getUserID(javax.servlet.http.HttpServletRequest request){
//        HttpSession session = request.getSession(false);
//        User user = null;
//        Long userid = Long.valueOf(0);
//        if(session != null && session.getAttribute("user") != null){
//            user = (User) session.getAttribute("user");
//        }
//        userid = user.getId();
//        return userid;
//    }

}
