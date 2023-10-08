package com.gridnine.testing.storage;

import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;

public interface RuleStorage<R extends FlightRule> {

    void addRule(R rule);

    void addRules(Collection<R> rules);

    boolean removeRule(String ruleName);

    boolean ruleExists(String ruleName);

    Collection<R> getRules();
}
