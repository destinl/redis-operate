package org.example.rank.controller;

import org.example.rank.service.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1818:40
 */
@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private GeoLocationService geoLocationService;

    @PostMapping("/addLocation")
    public ResponseEntity<String> addLocation(@RequestParam String userId,
                                              @RequestParam double longitude,
                                              @RequestParam double latitude) {
        geoLocationService.addLocation(userId, longitude, latitude);
        return ResponseEntity.ok("User location added");
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<String>> getUsersNearby(@RequestParam double longitude,
                                                       @RequestParam double latitude,
                                                       @RequestParam double radius) {
        List<String> nearbyUsers = geoLocationService.getUsersNearby(longitude, latitude, radius);
        return ResponseEntity.ok(nearbyUsers);
    }
}
