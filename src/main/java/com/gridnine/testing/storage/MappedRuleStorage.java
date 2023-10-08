package com.gridnine.testing.storage;

import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;
import java.util.Map;

public abstract class MappedRuleStorage<R extends FlightRule> implements RuleStorage<R> {
    protected final Map<String, R> storage;

    public MappedRuleStorage(Map<String, R> storage) {
        this.storage = storage;
    }

    @Override
    public void addRule(R rule) {
        storage.put(rule.getName(), rule);
    }

    @Override
    public void addRules(Collection<R> rules) {
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
    public Collection<R> getRules() {
        return storage.values();
    }
}
