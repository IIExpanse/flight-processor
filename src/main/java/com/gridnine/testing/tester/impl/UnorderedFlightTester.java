package com.gridnine.testing.tester.impl;

import com.gridnine.testing.storage.impl.UnorderedRuleStorage;
import com.gridnine.testing.tester.AbstractFlightTester;


/**
 * FlightTester implementation that utilizes unordered (HashMap-based) RuleStorage implementation.
 */
public class UnorderedFlightTester extends AbstractFlightTester {
    public UnorderedFlightTester() {
        super(new UnorderedRuleStorage());
    }
}
