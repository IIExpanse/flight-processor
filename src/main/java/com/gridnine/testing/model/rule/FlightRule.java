package com.gridnine.testing.model.rule;

import com.gridnine.testing.model.flight.Flight;

import java.util.function.Predicate;

public class FlightRule implements Predicate<Flight> {
    private final Predicate<Flight> condition;
    private final String name;

    public FlightRule(String name, Predicate<Flight> condition) {
        this.name = name;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean test(Flight flight) {
        return condition.test(flight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightRule that = (FlightRule) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
