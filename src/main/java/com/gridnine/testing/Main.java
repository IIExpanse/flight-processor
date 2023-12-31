package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.builder.RuleBuilder;
import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.rule.FlightRule;
import com.gridnine.testing.tester.FlightTester;
import com.gridnine.testing.tester.impl.UnorderedFlightTester;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        FlightTester tester = new UnorderedFlightTester();
        Collection<Flight> testFlights = FlightBuilder.createFlights();
        Collection<FlightRule> testRules = RuleBuilder.createRules();
        System.out.println("Source: " + testFlights + "\n");

        for (FlightRule rule : testRules) {
            tester.addRule(rule);
            Collection<Flight> filtered = tester.filterFlights(testFlights, true);
            System.out.printf("Condition \"%s\".\nFlights: %s\n", rule.getName(), filtered);
            System.out.println("Flights filtered: " + (testFlights.size() - filtered.size()));
            System.out.println("Excluded flights: " + tester.filterFlights(testFlights, false) + "\n");
            tester.removeRule(rule.getName());
        }
    }
}
