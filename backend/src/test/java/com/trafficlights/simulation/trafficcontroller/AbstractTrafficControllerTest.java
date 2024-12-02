package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.utils.Command;
import com.trafficlights.simulation.utils.SimulationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTrafficControllerTest {

    protected TrafficController controller;

    protected abstract TrafficController createController();

    @BeforeEach
    public void setUp() {
        controller = createController();
    }

    @Test
    public void testRun() {
        Command addVehicleCommand = new Command();
        addVehicleCommand.setVehicleId("1");
        addVehicleCommand.setType("addVehicle");
        addVehicleCommand.setStartRoad("north");
        addVehicleCommand.setEndRoad("south");

        Command stepCommand = new Command();
        stepCommand.setType("step");

        controller.addCommand(addVehicleCommand);
        controller.addCommand(stepCommand);

        controller.run();

        List<SimulationResult.StepStatus> returnValue = controller.getStepStatus().getStepStatuses();
        assertEquals(1, returnValue.size());
        assertEquals(1, returnValue.get(0).getLeftVehicles().size());
        assertEquals("1", returnValue.get(0).getLeftVehicles().get(0));
    }

    @Test
    public void testRunTwoCars() {
        Command addFirstVehicleCommand = new Command();
        addFirstVehicleCommand.setVehicleId("1");
        addFirstVehicleCommand.setType("addVehicle");
        addFirstVehicleCommand.setStartRoad("north");
        addFirstVehicleCommand.setEndRoad("south");

        Command addSecondVehicleCommand = new Command();
        addSecondVehicleCommand.setVehicleId("2");
        addSecondVehicleCommand.setType("addVehicle");
        addSecondVehicleCommand.setStartRoad("south");
        addSecondVehicleCommand.setEndRoad("north");

        Command stepCommand = new Command();
        stepCommand.setType("step");

        controller.addCommand(addFirstVehicleCommand);
        controller.addCommand(addSecondVehicleCommand);
        controller.addCommand(stepCommand);
        controller.addCommand(stepCommand);

        controller.run();

        List<SimulationResult.StepStatus> returnValue = controller.getStepStatus().getStepStatuses();
        assertEquals(2, returnValue.size());
        assertEquals(2, returnValue.get(0).getLeftVehicles().size());
        assertEquals(0, returnValue.get(1).getLeftVehicles().size());
    }
}