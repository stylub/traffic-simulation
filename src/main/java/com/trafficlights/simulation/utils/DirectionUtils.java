package com.trafficlights.simulation.utils;

public class DirectionUtils {
    public static Directions parseDirection(String road) {
        try {
            return Directions.valueOf(road.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid direction: " + road + ". Valid directions are: NORTH, WEST, SOUTH, EAST.");
        }
    }
}
