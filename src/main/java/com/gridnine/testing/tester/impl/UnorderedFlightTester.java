package com.gridnine.testing.tester.impl;

import com.gridnine.testing.model.rule.FlightRule;
import com.gridnine.testing.storage.impl.UnorderedRuleStorage;
import com.gridnine.testing.tester.AbstractFlightTester;

public class UnorderedFlightTester extends AbstractFlightTester<FlightRule> {
    public UnorderedFlightTester() {
        super(new UnorderedRuleStorage());
    }
}
