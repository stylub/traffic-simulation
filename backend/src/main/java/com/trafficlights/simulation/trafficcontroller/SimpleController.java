package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.Roads.Roads;
import com.trafficlights.simulation.car.Car;
import com.trafficlights.simulation.utils.Command;
import com.trafficlights.simulation.utils.StepStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleController extends TrafficController{
    public SimpleController() {
        super();
    }
    public SimpleController(Roads roads, StepStatus stepStatus) {
        super(roads,stepStatus);
    }
    @Override
    public void makeStep(){
        int startingRound = rounds.getRoundNumber();
        List<String> vehicles = new ArrayList<>();
        do{
        for (String direction : rounds.nextRound()) {
            Car car = roads.getCar(direction);
            if (car != null) {
                vehicles.add(car.getVehicleId());
            }
        }
        }while(vehicles.isEmpty() &&  rounds.getRoundNumber() != startingRound);
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
