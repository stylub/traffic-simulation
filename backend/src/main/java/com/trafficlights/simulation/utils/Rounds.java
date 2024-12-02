package com.trafficlights.simulation.utils;

import java.util.ArrayList;
import java.util.List;

public class Rounds {
    private int roundNumber = 0;
    private final List<List<String>> rounds;

    public Rounds() {
        this.rounds = defaultRounds();
    }
    public Rounds(List<List<String>> rounds){
        this.rounds = rounds;
    }
    private List<List<String>> defaultRounds() {
        List<List<String>> rounds = new ArrayList<>();
        rounds.add(List.of("northsouth", "southnorth"));
        rounds.add(List.of("northwest", "westnorth", "southeast", "eastsouth"));
        rounds.add(List.of("westeast", "eastwest"));
        rounds.add(List.of("westsouth", "southwest", "eastnorth", "northeast"));
        return rounds;
    }

    public List<String> nextRound(){
        List<String> round =  rounds.get(roundNumber);
        this.roundNumber = (roundNumber + 1) % rounds.size();
        return round;
    }

    public List<List<String>> getRounds(){
        return rounds;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public List<String> getRoundByIndex(int index){
        return rounds.get(index);
    }
}