package com.gridnine.testing.storage;

import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;
import java.util.Map;

public abstract class MapRuleStorage<T extends FlightRule> implements RuleStorage<T> {
    protected final Map<String, T> storage;

    public MapRuleStorage(Map<String, T> storage) {
        this.storage = storage;
    }

    @Override
    public void addRule(T rule) {
        storage.put(rule.getName(), rule);
    }

    @Override
    public void addRules(Collection<T> rules) {
        rules.forEach(this::addRule);
    }

    @Override
    public boolean removeRule(String ruleName) {
        return storage.remove(ruleName) != null;
    }

    @Override
    public boolean ruleExists(String ruleName) {
        return storage.containsKey(ruleName);
    }

    @Override
    public Collection<T> getRules() {
        return storage.values();
    }
}
