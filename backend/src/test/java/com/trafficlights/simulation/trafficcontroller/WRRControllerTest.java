package com.trafficlights.simulation.trafficcontroller;

public class WRRControllerTest extends AbstractTrafficControllerTest {
    @Override
    protected TrafficController createController() {
        return new WRRController();
    }
}
