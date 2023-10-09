package com.gridnine.testing.builder;

import com.gridnine.testing.model.flight.Segment;
import com.gridnine.testing.model.rule.FlightRule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


/**
 * Factory class that returns 3 basic FlightRule's for testing purposes.
 */
public class RuleBuilder {

    public static Collection<FlightRule> createRules() {
        FlightRule rule1 = new FlightRule(
                "Departure is after current time",
                flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()))
        );
        FlightRule rule2 = new FlightRule(
                "Departure is before arrival",
                flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate()))
        );
        FlightRule rule3 = new FlightRule(
                "Time on ground is less than or equal to 2 hours",
                flight -> {
                    List<Segment> list = flight.getSegments();
                    LocalDateTime prev = list.get(0).getArrivalDate();
                    Duration timeLanded = Duration.ZERO;
                    Duration limit = Duration.ofHours(2);
                    int l = list.size();

                    for (int i = 1; i < l; i++) {
                        timeLanded = timeLanded.plus(Duration.between(prev, list.get(i).getDepartureDate()));
                        if (timeLanded.compareTo(limit) > 0) {
                            return false;
                        }
                        prev = list.get(i).getArrivalDate();
                    }
                    return true;
                }
        );

        return List.of(rule1, rule2, rule3);
    }
}
