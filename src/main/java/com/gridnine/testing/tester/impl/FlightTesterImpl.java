package com.gridnine.testing.tester.impl;

import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.rule.FlightRule;
import com.gridnine.testing.storage.MapRuleStorage;
import com.gridnine.testing.tester.FlightTester;

import java.util.Collection;
import java.util.stream.Collectors;

public class FlightTesterImpl implements FlightTester {
    private final MapRuleStorage<FlightRule> storage;

    public FlightTesterImpl(MapRuleStorage<FlightRule> storage) {
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
    public Collection<Flight> filterFlights(Collection<Flight> collection) {
        return collection.stream()
                .filter(this::isMatching)
                .collect(Collectors.toList());
    }

    private boolean isMatching(Flight flight) {
        return storage.getRules().stream()
                .allMatch(condition -> condition.test(flight));
    }
}
