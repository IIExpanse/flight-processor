package com.gridnine.testing.tester;

import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.rule.FlightRule;
import com.gridnine.testing.storage.RuleStorage;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractFlightTester<R extends FlightRule> implements FlightTester<R> {
    private final RuleStorage<R> storage;

    public AbstractFlightTester(RuleStorage<R> storage) {
        this.storage = storage;
    }

    @Override
    public void addRule(R rule) {
        storage.addRule(rule);
    }

    @Override
    public void addRules(Collection<R> rules) {
        storage.addRules(rules);
    }

    @Override
    public boolean removeRule(String name) {
        return storage.removeRule(name);
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
