package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.Roads.Roads;
import com.trafficlights.simulation.car.Car;
import com.trafficlights.simulation.utils.Command;
import com.trafficlights.simulation.utils.Rounds;
import com.trafficlights.simulation.utils.StepStatus;


import java.util.LinkedList;
import java.util.Objects;

public abstract class TrafficController {
    protected LinkedList<Command> commands;
    protected  final Rounds rounds;
    protected final Roads roads;
    protected final StepStatus stepStatus;


    public TrafficController() {
        this.commands = new LinkedList<>();
        this.roads = new Roads();
        this.stepStatus = new StepStatus();
        this.rounds = new Rounds();
    }
    public TrafficController(Roads roads, StepStatus stepStatus) {
        this.roads = roads;
        this.stepStatus = stepStatus;
        this.rounds = new Rounds();
    }
    public void loadCommands(LinkedList<Command> commands) {
        this.commands = commands;
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public Command nextCommand() {
        return this.commands.pollFirst();
    }

    public StepStatus getStepStatus() {
        return stepStatus;
    }
    public abstract void makeStep();

    public abstract void run();


}
