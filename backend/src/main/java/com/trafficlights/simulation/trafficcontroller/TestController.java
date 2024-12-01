package com.trafficlights.simulation.trafficcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/test-endpoint")
    public String[] getTestArray() {
        return new String[]{"north-west", "west-north", "south-east", "east-south"};
    }
}