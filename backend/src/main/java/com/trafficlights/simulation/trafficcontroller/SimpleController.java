package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.Roads.Roads;
import com.trafficlights.simulation.car.Car;
import com.trafficlights.simulation.utils.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleController extends TrafficController{
    private int currentRound = 0;
    private final List<List<String>> rounds;
    private final Roads roads;
    private final StepStatus stepStatus;

    public SimpleController() {
        this.rounds = new ArrayList<>();
        this.roads = new Roads();
        this.stepStatus = new StepStatus();
        initializeRounds();
    }
    public SimpleController(Roads roads, StepStatus stepStatus) {
        this.roads = roads;
        this.stepStatus = stepStatus;
        this.rounds = new ArrayList<>();
        initializeRounds();
    }
    private void initializeRounds() {
        rounds.add(List.of("northsouth", "southnorth"));
        rounds.add(List.of("northwest", "westnorth", "southeast", "eastsouth"));
        rounds.add(List.of("westeast", "eastwest"));
        rounds.add(List.of("westsouth", "southwest", "eastnorth", "northeast"));
    }

    public StepStatus getStepStatus() {
        return stepStatus;
    }

    public void makeStep(){
        int startingRound = this.currentRound;
        List<String> vehicles = new ArrayList<>();
        do{
        for (String direction : rounds.get(currentRound)) {
            Car car = roads.getCar(direction);
            if (car != null) {
                vehicles.add(car.getVehicleId());
            }
        }
        currentRound = (currentRound + 1) % rounds.size();
        }while(vehicles.isEmpty() && currentRound != startingRound);
        stepStatus.addStep(vehicles);
    }
    @Override
    public void run() {
        Command command = nextCommand();
        while (command != null) {
            if (Objects.equals(command.getType(), "step")){
                makeStep();
            }
            if(Objects.equals(command.getType(), "addVehicle")){
                Car car = new Car(command);
                roads.addCar(car);
            }
            command = nextCommand();
        }
    }
}
