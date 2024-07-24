package com.example.glife.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.glife.common.R;
import com.example.glife.common.RedisConstants;
import com.example.glife.entity.Marker;
import com.example.glife.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.GeoLocation;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import org.yaml.snakeyaml.error.Mark;

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
     * @param latitude
     * @param longitude
     * @return
     */
    public R<String> store(WebSocketSession session, double longitude, double latitude) {
        //Long userID = (Long) session.getAttributes().get("userID");
        String userName = (String) session.getAttributes().get("name");

        if (userName != null && StrUtil.isNotBlank(userName.toString())) {
            String key = LOCATION_KEY;
            Point point = new Point(longitude, latitude);
            String shortUUID = UUID.randomUUID().toString().substring(0, 8);

            String locationUniqueID = userName + "-" + shortUUID;

            //store in opsForGEO()
            template.opsForGeo().add(key, point, locationUniqueID);

            return R.success("add success");
        }else{
            return R.error("Can't find user");
        }
    }

    public R<List<Marker>> getNearByPosition(double longitude, double latitude){
        Point currentLocation = new Point(longitude, latitude);

        Distance radius = new Distance(RADIUS, RedisGeoCommands.DistanceUnit.METERS);

        String key = LOCATION_KEY;

        // Construct Circle object for the radius query
        Circle circle = new Circle(currentLocation, radius);

////        // Perform radius query
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults =
                template.opsForGeo().radius(key, circle);


        List<Marker> points = new ArrayList<>();
        if(geoResults != null && geoResults.getContent()!= null && !geoResults.getContent().isEmpty()){
            List<String> names = new ArrayList<>();
            for (GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult : geoResults) {

                String name = geoResult.getContent().getName();
                String[] parts = name.split("-");
                if (parts.length > 0) {
                    names.add(parts[0]);
                }
            }
            List<Point> positionList = template.opsForGeo().position(key, names.toArray(new String[0]));
//            for (Point point : positionList) {
//                if (point != null) {
//                    Marker thisMarker = new Marker();
//
//                    //points.add(point);
//                }
//            }
            for(int i = 0; i<positionList.size();i++){
                Point point = positionList.get(i);
                if(point != null){
                    Marker thisMarker = new Marker();
                    thisMarker.setPoint(point);
                    thisMarker.setName(names.get(i));
                    points.add(thisMarker);
                }
            }
        }

        return R.success(points);
    }


}
