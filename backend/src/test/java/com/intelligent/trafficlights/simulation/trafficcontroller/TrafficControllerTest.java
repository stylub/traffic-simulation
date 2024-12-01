package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.utils.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficControllerTest {

    private SimpleController simpleController;
    private RoundRobinController roundRobinController;

    @BeforeEach
    public void setUp() {
        simpleController = new SimpleController();
        roundRobinController = new RoundRobinController();
    }

    @Test
    public void testRunSimpleController() {
        testRun(simpleController);
    }

    @Test
    public void testRunTwoCarsSimpleController(){
        testRunTwoCars(simpleController);
    }

    @Test
    public void testRunRoundRobinController(){
        testRun(roundRobinController);
    }
    @Test
    public void testRunTwoCarsRoundRobinController(){
        testRunTwoCars(roundRobinController);
    }

    public void testRunTwoCars(TrafficController controller) {
        Command addFirstVehicleCommand = new Command();
        addFirstVehicleCommand.setVehicleId("1");
        addFirstVehicleCommand.setType("addVehicle");
        addFirstVehicleCommand.setStartRoad("north");
        addFirstVehicleCommand.setEndRoad("south");

        Command addSecoundVehicleCommand = new Command();
        addSecoundVehicleCommand.setVehicleId("2");
        addSecoundVehicleCommand.setType("addVehicle");
        addSecoundVehicleCommand.setStartRoad("south");
        addSecoundVehicleCommand.setEndRoad("north");

        Command stepCommand = new Command();
        stepCommand.setType("step");

        controller.addCommand(addFirstVehicleCommand);
        controller.addCommand(addSecoundVehicleCommand);
        controller.addCommand(stepCommand);
        controller.addCommand(stepCommand);

        controller.run();

        List<List<String>> returnValue = controller.getStepStatus().getLeftVehicles();
        assertEquals(2, returnValue.size());
        assertEquals(2, returnValue.get(0).size());
        assertEquals(0, returnValue.get(1).size());
    }

    public void testRun(TrafficController controller) {
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

        List<List<String>> returnValue = controller.getStepStatus().getLeftVehicles();
        assertEquals(1, returnValue.size());
        assertEquals(1, returnValue.get(0).size());
        assertEquals("1", returnValue.get(0).get(0));
    }
}