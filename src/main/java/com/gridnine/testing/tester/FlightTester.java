package com.gridnine.testing.tester;

import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;

public interface FlightTester {

    void addRule(FlightRule rule);

    boolean removeRule(String name);

    void addRules(Collection<FlightRule> rules);

    Collection<Flight> filterFlights(Collection<Flight> collection);
}
