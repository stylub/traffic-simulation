package com.trafficlights.simulation.Queue;

import com.trafficlights.simulation.utils.Rounds;

import java.util.ArrayList;
import java.util.Collections;

public class WeightQueue extends Queue{
    private int maxWeight = 10;
    private final ArrayList<Integer> weights;
    public WeightQueue(Rounds rounds) {
        super(rounds);
        weights = new ArrayList<>(Collections.nCopies(queues.size(),0));
    }
    public WeightQueue(Rounds rounds,int maxWeight) {
        super(rounds);
        this.maxWeight = maxWeight;
        weights = new ArrayList<>(Collections.nCopies(queues.size(), 0));
    }

    @Override
    public void removeFromQueue(int index, int value){
        weights.set(index,weights.get(index) - value);
    }

    @Override
    public int takeMax() {
        if(allEmpty()) resetWeights();
        int maxIndex = 0;
        int currentMax = weights.get(0);
        for (int i = 1; i < weights.size(); i++) {
            if (weights.get(i) > currentMax) {
                currentMax = weights.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void resetWeights(){
        int i = 0;
        for(int q : queues){
           int takeAmount = Math.min(q,maxWeight);
            super.removeFromQueue(i,takeAmount);
            weights.set(i,takeAmount);
           i++;
        }
    }

    public boolean allEmpty(){
        for (int w : weights) {
            if (w != 0) {
                return false;
            }
        }
        return true;
    }
}
