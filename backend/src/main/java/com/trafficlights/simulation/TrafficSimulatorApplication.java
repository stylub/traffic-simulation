package com.trafficlights.simulation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trafficlights.simulation.trafficcontroller.RoundRobinController;
import com.trafficlights.simulation.utils.CommandRequest;
import com.trafficlights.simulation.utils.SimulationResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class TrafficSimulatorApplication {
	public static void main(String[] args) {
		if ("true".equals(args[0])) {
			SpringApplication.run(TrafficSimulatorApplication.class, args);
		}
		else {
			String inputFilePath = args[1];
			String outputFilePath = args[2];

			try {
				String inputContent = new String(Files.readAllBytes(Paths.get(inputFilePath)));
				ObjectMapper objectMapper = new ObjectMapper();
				CommandRequest commandRequest = objectMapper.readValue(inputContent, CommandRequest.class);

				RoundRobinController roundRobinController = new RoundRobinController();
				SimulationResult simulationResult = roundRobinController.startSimulation(commandRequest);

				String jsonOutput = objectMapper.writeValueAsString(simulationResult);

				Files.write(Paths.get(outputFilePath), jsonOutput.getBytes());
				System.out.println("Output written to " + outputFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}