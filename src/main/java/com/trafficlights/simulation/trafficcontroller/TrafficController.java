package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.utils.Command;


import java.util.List;
import java.util.LinkedList;

public abstract class TrafficController {
    LinkedList<Command> commands;
    private StepStatus stepStatus;

    public TrafficController() {
        this.commands = new LinkedList<>();
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

    public void run() {
    }
}
