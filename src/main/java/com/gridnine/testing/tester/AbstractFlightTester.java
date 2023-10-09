package com.gridnine.testing.tester;

import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.rule.FlightRule;
import com.gridnine.testing.storage.RuleStorage;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Abstract class that defines basic realization of FlightTester methods.
 */
public abstract class AbstractFlightTester implements FlightTester {
    private final RuleStorage storage;

    public AbstractFlightTester(RuleStorage storage) {
        this.storage = storage;
    }

    @Override
    public void addRule(FlightRule rule) {
        storage.addRule(rule);
    }

    @Override
    public void addRules(Collection<FlightRule> rules) {
        storage.addRules(rules);
    }

    @Override
    public boolean removeRule(String name) {
        return storage.removeRule(name);
    }

    @Override
    public void removeRules(Collection<String> ruleNames) {
        storage.removeRules(ruleNames);
    }

    @Override
    public Collection<Flight> filterFlights(Collection<Flight> collection, boolean returnMatching) {
        return collection.stream()
                .filter(flight -> returnMatching == this.isMatching(flight))
                .collect(Collectors.toList());
    }

    private boolean isMatching(Flight flight) {
        return storage.getRules().stream()
                .allMatch(condition -> condition.test(flight));
    }
}
