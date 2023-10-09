package com.gridnine.testing.storage;

import com.gridnine.testing.model.rule.FlightRule;

import java.util.Collection;

/**
 * Interface that defines methods for all possible RuleStorage implementations
 */
public interface RuleStorage {

    void addRule(FlightRule rule);

    void addRules(Collection<FlightRule> rules);

    boolean removeRule(String ruleName);

    void removeRules(Collection<String> ruleNames);

    boolean ruleExists(String ruleName);

    Collection<FlightRule> getRules();
}
