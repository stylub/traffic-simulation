package com.trafficlights.simulation.utils;

import java.util.List;

public class CommandRequest {
    private List<Command> commands;

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}