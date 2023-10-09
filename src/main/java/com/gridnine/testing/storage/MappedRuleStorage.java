package com.gridnine.testing.storage;

import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;
import java.util.Map;

/**
 * Abstract class that defines a branch of implementations using Map structure for storing rules.
 */
public abstract class MappedRuleStorage implements RuleStorage {
    protected final Map<String, FlightRule> storage;

    public MappedRuleStorage(Map<String, FlightRule> storage) {
        this.storage = storage;
    }

    @Override
    public void addRule(FlightRule rule) {
        storage.put(rule.getName(), rule);
    }

    @Override
    public void addRules(Collection<FlightRule> rules) {
        rules.forEach(this::addRule);
    }

    @Override
    public boolean removeRule(String ruleName) {
        return storage.remove(ruleName) != null;
    }

    @Override
    public void removeRules(Collection<String> ruleNames) {
        ruleNames.forEach(this::removeRule);
    }

    @Override
    public boolean ruleExists(String ruleName) {
        return storage.containsKey(ruleName);
    }

    @Override
    public Collection<FlightRule> getRules() {
        return storage.values();
    }
}
