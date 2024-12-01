package com.trafficlights.simulation.Queue;

import com.trafficlights.simulation.utils.Rounds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Queue {
    protected final ArrayList<Integer> queues = new ArrayList<>();
    private final HashMap<String,Integer> directionToQueue = new HashMap<>();
    public Queue(Rounds rounds) {
        List<List<String>> allRounds = rounds.getRounds();
        queues.addAll(Collections.nCopies(allRounds.size(), 0));
        for (int i = 0; i < allRounds.size(); i++) {
            for (String direction : allRounds.get(i)) {
                directionToQueue.put(direction, i);
            }
        }
    }
    public void addToQueue(String direction){
        int queueIndex = directionToQueue.get(direction);
        queues.set(queueIndex, queues.get(queueIndex) + 1);
    }

    public void removeFromQueue(int index, int value){
        queues.set(index, queues.get(index) - value);
    }
    public int takeMax() {
        int maxIndex = 0;
        int currentMax = queues.get(0);
        for (int i = 1; i < queues.size(); i++) {
            if (queues.get(i) > currentMax) {
                currentMax = queues.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
