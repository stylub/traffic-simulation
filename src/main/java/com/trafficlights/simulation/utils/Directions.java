package com.trafficlights.simulation.utils;

public enum Directions {
    NORTH(0,"north"),
    WEST(1,"west"),
    SOUTH(2,"south"),
    EAST(3,"east");

    private final int value;
    private  final String name;

    Directions(int value,String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
