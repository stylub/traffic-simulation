package com.trafficlights.simulation.trafficcontroller;

import java.util.ArrayList;
import java.util.List;

public class StepStatus {
    private List<List<String>> leftVehicles = new ArrayList<>();

    public void addStep(List<String> vehicles) {
        leftVehicles.add(vehicles);
    }

    public List<List<String>> getLeftVehicles() {
        return leftVehicles;
    }
}
