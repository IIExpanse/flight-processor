package com.gridnine.testing.storage.impl;

import com.gridnine.testing.model.rule.PrioritizedFlightRule;
import com.gridnine.testing.storage.MapRuleStorage;

import java.util.TreeMap;

public class PrioritizedRuleStorage extends MapRuleStorage<PrioritizedFlightRule> {
    public PrioritizedRuleStorage() {
        super(new TreeMap<>());
    }
}
