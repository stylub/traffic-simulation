package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.Roads.Roads;
import com.trafficlights.simulation.car.Car;
import com.trafficlights.simulation.utils.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SimpleControllerTest {

    private SimpleController simpleController;
    private Roads roadsMock;
    private StepStatus stepStatusMock;

    @BeforeEach
    public void setUp() {
        roadsMock = mock(Roads.class);
        stepStatusMock = mock(StepStatus.class);
        simpleController = new SimpleController();
    }

    @Test
    public void testMakeStep() {
        when(roadsMock.getCar(anyString())).thenReturn(new Car("1","north","south"), null);

        simpleController.makeStep();

        verify(stepStatusMock, times(1)).addStep(anyList());
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

        simpleController.addCommand(addVehicleCommand);
        simpleController.addCommand(stepCommand);

        simpleController.run();

        List<List<String>> returnValue = simpleController.getStepStatus().getLeftVehicles();
        assertEquals(1, returnValue.size());
        assertEquals(1, returnValue.get(0).size());
        assertEquals("1", returnValue.get(0).get(0));
    }

    @Test
    public void testRunTwoCars() {
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

        simpleController.addCommand(addFirstVehicleCommand);
        simpleController.addCommand(addSecoundVehicleCommand);
        simpleController.addCommand(stepCommand);
        simpleController.addCommand(stepCommand);

        simpleController.run();

        List<List<String>> returnValue = simpleController.getStepStatus().getLeftVehicles();
        assertEquals(2, returnValue.size());
        assertEquals(2, returnValue.get(0).size());
        assertEquals(0, returnValue.get(1).size());
    }
}