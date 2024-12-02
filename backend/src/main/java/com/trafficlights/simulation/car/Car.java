package com.trafficlights.simulation.car;


import com.trafficlights.simulation.utils.Command;
import com.trafficlights.simulation.utils.Directions;

public class Car {
    private final String vehicleId;
    private final Directions startRoad;
    private final Directions endRoad;

    public Car(String vehicleId, String startRoad, String endRoad) {
        this.vehicleId = vehicleId;
        this.startRoad = Directions.parseDirection(startRoad);
        this.endRoad = Directions.parseDirection(endRoad);
    }

    public Car(Command command) {
        this.vehicleId = command.getVehicleId();
        this.startRoad = Directions.parseDirection(command.getStartRoad());
        this.endRoad = Directions.parseDirection(command.getEndRoad());
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

    public String getDirection(){
        return startRoad.toString() + endRoad.toString();
    }
}
