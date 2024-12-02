package com.trafficlights.simulation.trafficcontroller;

public class RoundRobinControllerTest extends AbstractTrafficControllerTest {
    @Override
    protected TrafficController createController() {
        return new RoundRobinController();
    }
}