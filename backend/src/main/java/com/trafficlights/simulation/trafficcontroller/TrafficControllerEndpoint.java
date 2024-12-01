package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.utils.CommandRequest;
import com.trafficlights.simulation.utils.SimulationResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TrafficControllerEndpoint {
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/run-commands/simple")
    public SimulationResult runSimple(@RequestBody CommandRequest commandRequest) {
        SimpleController simpleController = new SimpleController();
        return simpleController.startSimulation(commandRequest);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/run-commands/round-robin")
    public SimulationResult runRoundRobin(@RequestBody CommandRequest commandRequest) {
        RoundRobinController roundRobinController = new RoundRobinController();
        return roundRobinController.startSimulation(commandRequest);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/run-commands/wrr")
    public SimulationResult runWRR(@RequestBody CommandRequest commandRequest) {
        WRRController wrrController = new WRRController();
        return wrrController.startSimulation(commandRequest);
    }
}