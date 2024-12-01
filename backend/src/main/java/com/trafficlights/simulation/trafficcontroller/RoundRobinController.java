package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.car.Car;
import com.trafficlights.simulation.utils.Command;
import com.trafficlights.simulation.Queue.Queue;

import java.util.*;

public class RoundRobinController extends TrafficController{
    protected Queue queue;

    public RoundRobinController(){
        super();
        this.queue = new Queue(rounds);
    }
    @Override
    public void makeStep() {
        List<String> vehicles = new ArrayList<>();
        int roundNow = queue.takeMax();
        for(String direction : rounds.getRoundByIndex(roundNow)){
            Car car = roads.getCar(direction);
            if (car != null) {
                vehicles.add(car.getVehicleId());
            }
        }
        queue.removeFromQueue(roundNow,vehicles.size());
        simulationResult.addStep(vehicles);
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
                queue.addToQueue(command.getVehicleDirection());
            }
            command = nextCommand();
        }
    }
}
