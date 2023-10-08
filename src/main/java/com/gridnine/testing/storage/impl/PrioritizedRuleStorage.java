package com.gridnine.testing.storage.impl;

import com.gridnine.testing.model.rule.PrioritizedFlightRule;
import com.gridnine.testing.storage.MappedRuleStorage;

import java.util.TreeMap;

public class PrioritizedRuleStorage extends MappedRuleStorage<PrioritizedFlightRule> {
    public PrioritizedRuleStorage() {
        super(new TreeMap<>());
    }
}
