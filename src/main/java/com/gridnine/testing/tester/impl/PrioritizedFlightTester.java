package com.gridnine.testing.tester.impl;

import com.gridnine.testing.model.rule.PrioritizedFlightRule;
import com.gridnine.testing.storage.impl.PrioritizedRuleStorage;
import com.gridnine.testing.tester.AbstractFlightTester;

public class PrioritizedFlightTester extends AbstractFlightTester<PrioritizedFlightRule> {
    public PrioritizedFlightTester() {
        super(new PrioritizedRuleStorage());
    }
}
