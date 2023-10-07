package com.gridnine.testing.storage;

import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;

public interface RuleStorage<T extends FlightRule> {

    void addRule(T rule);

    void addRules(Collection<T> rules);

    boolean removeRule(String ruleName);

    boolean ruleExists(String ruleName);

    Collection<T> getRules();
}
