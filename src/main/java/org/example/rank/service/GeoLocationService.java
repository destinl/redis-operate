package org.example.rank.service;

import io.lettuce.core.GeoWithin;
import org.springframework.data.geo.GeoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.geo.Circle;
//import org.springframework.data.geo.GeoCoordinate;
import org.springframework.stereotype.Service;
import org.example.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1818:40
 */
@Service
public class GeoLocationService {

    @Autowired
    private RedisTemplate<String, User> redisTemplate_String_User;

    public void addLocation(String userId, double longitude, double latitude) {
        User user = new User(userId, "username", longitude, latitude);
        // 使用Geospatial索引存储用户位置
        redisTemplate_String_User.opsForGeo().add("userLocations", new Point(user.getLongitude(), user.getLatitude()),
                user);
    }

    public List<String> getUsersNearby(double longitude, double latitude, double radius) {
//        // 查询给定位置附近的用户
//        GeoOperations<String, User> geoOps = redisTemplate_String_User.opsForGeo();
//        GeoResults<RedisGeoCommands.GeoLocation<User>> results = geoOps.radius("userLocations",
//                new Circle(new Point(latitude, longitude), radius),
//                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs());
//
//        List<String> nearbyUsers = new ArrayList<>();
//        for (GeoResult<RedisGeoCommands.GeoLocation<User>> result : results) {
//            nearbyUsers.add(String.valueOf(result.getContent()));
//        }

        // 查询给定位置附近的用户
        GeoResults<RedisGeoCommands.GeoLocation<User>> results = redisTemplate_String_User.opsForGeo().radius("userLocations",
                new Circle(new Point(latitude, longitude), radius),
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs());

//        List<GeoWithin<User>> nearbyUsersGeo = results.getContent().stream()
//                .map(geoLocation -> new GeoWithin<>(geoLocation.getContent(), geoLocation.()))
//                .collect(Collectors.toList());
//
//        List<User> nearbyUsers = new ArrayList<>();
//        for (GeoWithin geoWithin : nearbyUsersGeo) {
//            nearbyUsers.add(redisTemplate.opsForValue().get(geoWithin.getMember()));
//        }
        List<User> nearbyUsers = new ArrayList<>();
        for (GeoResult<RedisGeoCommands.GeoLocation<User>> result : results) {
            nearbyUsers.add(result.getContent().getName());
        }
        return Collections.singletonList(nearbyUsers.toString());
    }
}
