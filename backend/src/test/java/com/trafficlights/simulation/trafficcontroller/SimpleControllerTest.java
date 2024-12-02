package com.trafficlights.simulation.trafficcontroller;

public class SimpleControllerTest extends AbstractTrafficControllerTest {
    @Override
    protected TrafficController createController() {
        return new SimpleController();
    }
}