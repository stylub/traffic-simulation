package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.utils.Command;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SimpleControllerEndpoint {
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/run-commands")
    public StepStatus runCommands(@RequestBody CommandRequest commandRequest) {
        SimpleController simpleController = new SimpleController();

        for (Command command : commandRequest.getCommands()) {
            simpleController.addCommand(command);
        }

        simpleController.run();
        return simpleController.getStepStatus();
    }

    public static class CommandRequest {
        private List<Command> commands;

        public List<Command> getCommands() {
            return commands;
        }

        public void setCommands(List<Command> commands) {
            this.commands = commands;
        }
    }
}