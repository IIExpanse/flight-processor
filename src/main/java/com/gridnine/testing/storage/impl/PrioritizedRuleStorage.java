package com.gridnine.testing.storage.impl;

import com.gridnine.testing.storage.MappedRuleStorage;

import java.util.TreeMap;

/**
 * Implementation that returns rules in sorted order (according to their priority field),
 *      but with increased O(log n) time for adding / removing rules (compared to Unordered implementation).
 */
public class PrioritizedRuleStorage extends MappedRuleStorage {
    public PrioritizedRuleStorage() {
        super(new TreeMap<>());
    }
}
