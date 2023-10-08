package com.gridnine.testing.storage.impl;

import com.gridnine.testing.model.rule.FlightRule;
import com.gridnine.testing.storage.MappedRuleStorage;

import java.util.HashMap;

public class UnorderedRuleStorage extends MappedRuleStorage<FlightRule> {
    public UnorderedRuleStorage() {
        super(new HashMap<>());
    }
}
