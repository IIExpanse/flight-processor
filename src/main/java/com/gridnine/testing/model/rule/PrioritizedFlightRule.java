package com.gridnine.testing.model.rule;

import com.gridnine.testing.model.flight.Flight;

import java.util.function.Predicate;

public class PrioritizedFlightRule extends FlightRule implements Comparable<PrioritizedFlightRule> {
    private final int priority;

    public PrioritizedFlightRule(String name, Predicate<Flight> condition, int priority) {
        super(name, condition);
        this.priority = priority;
    }

    @Override
    public int compareTo(PrioritizedFlightRule o) {
        return this.priority - o.priority;
    }
}
