package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.Roads.Roads;
import com.trafficlights.simulation.utils.Command;
import com.trafficlights.simulation.utils.CommandRequest;
import com.trafficlights.simulation.utils.Rounds;
import com.trafficlights.simulation.utils.SimulationResult;


import java.util.LinkedList;

public abstract class TrafficController {
    protected LinkedList<Command> commands;
    protected  final Rounds rounds;
    protected final Roads roads;
    protected final SimulationResult simulationResult;


    public TrafficController() {
        this.commands = new LinkedList<>();
        this.roads = new Roads();
        this.simulationResult = new SimulationResult();
        this.rounds = new Rounds();
    }
    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public Command nextCommand() {
        return this.commands.pollFirst();
    }

    public SimulationResult getStepStatus() {
        return simulationResult;
    }
    public abstract void makeStep();

    public abstract void run();

    public SimulationResult startSimulation(CommandRequest commandRequest){
        for (Command command : commandRequest.getCommands()) {
            addCommand(command);
        }

        run();

        return getStepStatus();
    }
}
