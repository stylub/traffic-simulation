package com.trafficlights.simulation.car;


import com.trafficlights.simulation.utils.DirectionUtils;
import com.trafficlights.simulation.utils.Directions;

// "vehicleId": "vehicle1",
// "startRoad": "south",
// "endRoad": "north"
public class Car {
    private final String vehicleId;
    private final Directions startRoad;
    private final Directions endRoad;

    public Car(String vehicleId, String startRoad, String endRoad) {
        this.vehicleId = vehicleId;
        this.startRoad = DirectionUtils.parseDirection(startRoad);
        this.endRoad = DirectionUtils.parseDirection(endRoad);
    }

    public Directions getEndRoad() {
        return endRoad;
    }

    public Directions getStartRoad() {
        return startRoad;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
