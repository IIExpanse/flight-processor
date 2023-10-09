package com.gridnine.testing.storage.impl;

import com.gridnine.testing.model.rule.FlightRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnorderedRuleStorageTest {
    private UnorderedRuleStorage storage;

    @BeforeEach
    public void injectBeans() {
        storage = new UnorderedRuleStorage();
    }

    @Test
    public void addRuleTest() {
        FlightRule rule = getDepartureInFutureRule();

        storage.addRule(rule);
        assertEquals(1, storage.getRules().size());
        assertTrue(storage.getRules().contains(rule));
    }

    @Test
    public void shouldNotAddExistingRule() {
        FlightRule rule = getDepartureInFutureRule();

        storage.addRule(rule);
        storage.addRule(rule);
        assertEquals(1, storage.getRules().size());
    }

    @Test
    public void addRulesTest() {
        FlightRule rule1 = getDepartureInFutureRule();
        FlightRule rule2 = getDepartureBeforeArrivalRule();

        storage.addRules(List.of(rule1, rule2));
        assertEquals(2, storage.getRules().size());
        assertTrue(storage.getRules().contains(rule1));
        assertTrue(storage.getRules().contains(rule2));
    }

    @Test
    public void removeRuleTest() {
        FlightRule rule = getDepartureInFutureRule();

        storage.addRule(rule);
        assertTrue(storage.removeRule(rule.getName()));
        assertTrue(storage.getRules().isEmpty());
    }

    @Test
    public void removeRulesTest() {
        Collection<FlightRule> collection = List.of(getDepartureBeforeArrivalRule(), getDepartureInFutureRule());

        storage.addRules(collection);
        storage.removeRules(collection.stream()
                .map(FlightRule::getName)
                .collect(Collectors.toList()));
        assertTrue(storage.getRules().isEmpty());
    }

    @Test
    public void ruleExistsTest() {
        FlightRule rule = getDepartureInFutureRule();

        storage.addRule(rule);
        assertTrue(storage.ruleExists(rule.getName()));
    }

    private FlightRule getDepartureInFutureRule() {
        return new FlightRule(
                "Departure is after current time",
                flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()))
        );
    }

    private FlightRule getDepartureBeforeArrivalRule() {
        return new FlightRule(
                "Departure is before arrival",
                flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate()))
        );
    }
}