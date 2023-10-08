package com.gridnine.testing.tester;

import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;

public interface FlightTester<R extends FlightRule> {

    void addRule(R rule);

    boolean removeRule(String name);

    void addRules(Collection<R> rules);

    Collection<Flight> filterFlights(Collection<Flight> collection, boolean returnMatching);
}
