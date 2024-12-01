package com.trafficlights.simulation.utils;

import java.util.ArrayList;
import java.util.List;

public class SimulationResult {
    private final List<StepStatus> stepStatuses = new ArrayList<>();

    public void addStep(List<String> vehicles) {
        stepStatuses.add(new StepStatus(vehicles));
    }

    public List<StepStatus> getStepStatuses() {
        return stepStatuses;
    }
    public static class StepStatus {
        private List<String> leftVehicles;

        public StepStatus(List<String> leftVehicles){
            this.leftVehicles = leftVehicles;
        }

        public List<String> getLeftVehicles() {
            return leftVehicles;
        }
        public void setLeftVehicles(List<String> leftVehicles) {
            this.leftVehicles = leftVehicles;
        }
    }
}
