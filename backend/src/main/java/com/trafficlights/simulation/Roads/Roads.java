package com.trafficlights.simulation.Roads;

import com.trafficlights.simulation.car.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Roads {
    private final HashMap<String, LinkedList<Car>> roads;

    public Roads() {
        this.roads = new HashMap<>();
        for (String start : new String[]{"north", "south", "east", "west"}) {
            for (String end : new String[]{"north", "south", "east", "west"}) {
                if (!start.equals(end)) {
                    this.roads.put(start + end, new LinkedList<>());
                }
            }
        }
    }
    public void addCar(Car car) {
        this.roads.get(car.getStartRoad().toString() + car.getEndRoad().toString()).add(car);
    }
    public Car getCar(String direction) {
        return this.roads.get(direction).pollFirst();
    }
    public Car getCar(String startRoad, String endRoad) {
        return this.roads.get(startRoad + endRoad).pollFirst();
    }
}
