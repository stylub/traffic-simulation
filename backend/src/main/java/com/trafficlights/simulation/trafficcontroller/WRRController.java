package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.car.Car;
import com.trafficlights.simulation.utils.Command;

import java.util.*;

public class WRRController extends TrafficController{
    private final ArrayList<Integer> queues;
    private final HashMap<String,Integer> directionToQueue;
    private int maxPriority = 10;

    public WRRController(){
        super();
        queues = new ArrayList<Integer>();
        directionToQueue = new HashMap<>();
        initQueues();
    }

    private void initQueues() {
        List<List<String>> allRounds = rounds.getRounds();
        queues.addAll(Collections.nCopies(allRounds.size(), 0));
        for (int i = 0; i < allRounds.size(); i++) {
            for (String direction : allRounds.get(i)) {
                directionToQueue.put(direction, i);
            }
        }
    }
    private void addToQueue(String direction){
        int queueIndex = directionToQueue.get(direction);
        queues.set(queueIndex, queues.get(queueIndex) + 1);
    }

    private void removeFromQueue(int index, int value){
        queues.set(index, queues.get(index) - value);
    }

    private int takeMax() {
        int maxIndex = 0;
        int currentMax = queues.get(0);
        for (int i = 1; i < queues.size(); i++) {
            if (queues.get(i) > currentMax) {
                currentMax = queues.get(i);
                maxIndex = i;
            }
        }

        return maxIndex;
    }
    @Override
    public void makeStep() {
        List<String> vehicles = new ArrayList<>();
        int roundNow = takeMax();
        for(String direction : rounds.getRoundByIndex(roundNow)){
            Car car = roads.getCar(direction);
            if (car != null) {
                vehicles.add(car.getVehicleId());
            }
        }
        removeFromQueue(roundNow,vehicles.size());
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
                addToQueue(command.getVehicleDirection());
            }
            command = nextCommand();
        }
    }
}
