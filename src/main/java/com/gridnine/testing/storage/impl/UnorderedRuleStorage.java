package com.gridnine.testing.storage.impl;

import com.gridnine.testing.model.rule.FlightRule;
import com.gridnine.testing.storage.MapRuleStorage;

import java.util.HashMap;

public class UnorderedRuleStorage extends MapRuleStorage<FlightRule> {
    public UnorderedRuleStorage() {
        super(new HashMap<>());
    }
}
