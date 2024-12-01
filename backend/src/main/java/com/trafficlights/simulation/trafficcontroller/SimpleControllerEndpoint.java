package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.utils.Command;
import com.trafficlights.simulation.utils.StepStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SimpleControllerEndpoint {
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/run-commands/simple")
    public StepStatus runSimple(@RequestBody CommandRequest commandRequest) {
        SimpleController simpleController = new SimpleController();
        return startSimulation(commandRequest,simpleController);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/run-commands/round-robin")
    public StepStatus runRoundRobin(@RequestBody CommandRequest commandRequest) {
        WRRController simpleController = new WRRController();
        return startSimulation(commandRequest,simpleController);
    }

    public StepStatus startSimulation(CommandRequest commandRequest, TrafficController controller){
        for (Command command : commandRequest.getCommands()) {
            controller.addCommand(command);
        }

        controller.run();
        return controller.getStepStatus();
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