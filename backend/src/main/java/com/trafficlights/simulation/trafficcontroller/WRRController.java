package com.trafficlights.simulation.trafficcontroller;

import com.trafficlights.simulation.Queue.Queue;
import com.trafficlights.simulation.Queue.WeightQueue;
import com.trafficlights.simulation.car.Car;
import com.trafficlights.simulation.utils.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WRRController extends RoundRobinController {
    public WRRController(){
        super();
        this.queue = new WeightQueue(rounds);
    }
}
