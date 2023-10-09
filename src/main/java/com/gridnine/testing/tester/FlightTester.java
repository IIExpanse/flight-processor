package com.gridnine.testing.tester;

import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;


/**
 * Class that provides method for filtering flights according to stored rules
 *      as well as methods for rule storage manipulation.
 */
public interface FlightTester {

    void addRule(FlightRule rule);

    void addRules(Collection<FlightRule> rules);

    boolean removeRule(String name);

    void removeRules(Collection<String> ruleNames);

    Collection<Flight> filterFlights(Collection<Flight> collection, boolean returnMatching);
}
