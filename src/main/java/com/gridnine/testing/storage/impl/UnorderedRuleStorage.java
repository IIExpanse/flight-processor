package com.gridnine.testing.storage.impl;

import com.gridnine.testing.storage.MappedRuleStorage;

import java.util.HashMap;

/**
 * Implementation that allows adding / removing rules for constant time,
 *      but doesn't return them in any particular order.
 */
public class UnorderedRuleStorage extends MappedRuleStorage {
    public UnorderedRuleStorage() {
        super(new HashMap<>());
    }
}
