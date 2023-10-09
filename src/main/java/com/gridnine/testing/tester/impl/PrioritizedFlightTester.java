package com.gridnine.testing.tester.impl;

import com.gridnine.testing.storage.impl.PrioritizedRuleStorage;
import com.gridnine.testing.tester.AbstractFlightTester;

/**
 * FlightTester implementation that utilizes priority-ordered RuleStorage implementation.
 */
public class PrioritizedFlightTester extends AbstractFlightTester {
    public PrioritizedFlightTester() {
        super(new PrioritizedRuleStorage());
    }
}
