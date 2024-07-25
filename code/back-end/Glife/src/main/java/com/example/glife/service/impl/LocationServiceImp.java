package com.example.glife.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.glife.common.R;
import com.example.glife.common.RedisConstants;
import com.example.glife.entity.Marker;
import com.example.glife.entity.User;
import com.example.glife.entity.UserTree;
import com.example.glife.mapper.UserTreeMapper;
import com.example.glife.service.UserTreeService;
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

    @Autowired
    UserTreeMapper userTreeMapper;


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

            //userTree TreeCode -> 0
            String userIdStr = (String) session.getAttributes().get("userID");
            Long userId = Long.parseLong(userIdStr);
            LambdaQueryWrapper<UserTree> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserTree::getUserid, userId);

            UserTree userTree = userTreeMapper.selectOne(lambdaQueryWrapper);
            userTree.setTickSum(1);
            userTree.setTreeSum(userTree.getTreeSum()+1);
            userTreeMapper.updateById(userTree);

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
            List<String> originNames = new ArrayList<>();
            for (GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult : geoResults) {

                String name = geoResult.getContent().getName();
                originNames.add(name);
                String[] parts = name.split("-");
                if (parts.length > 0) {
                    names.add(parts[0]);
                }
            }
            List<Point> positionList = template.opsForGeo().position(key, originNames.toArray(new String[0]));
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
