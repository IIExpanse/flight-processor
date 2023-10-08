package com.gridnine.testing.storage.impl;

import com.gridnine.testing.model.flight.Segment;
import com.gridnine.testing.model.rule.FlightRule;
import com.gridnine.testing.model.rule.PrioritizedFlightRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrioritizedRuleStorageTest {

    private PrioritizedRuleStorage storage;

    @BeforeEach
    public void injectBeans() {
        storage = new PrioritizedRuleStorage();
    }

    @Test
    public void shouldReturnSortedRules() {
        List<PrioritizedFlightRule> sortedList = List.of(
                getDepartureInFutureRule(),
                getDepartureBeforeArrivalRule(),
                getTimeOnGroundNotMore2HoursRule()
        );
        for (int i = sortedList.size() - 1; i >= 0; i--) {
            storage.addRule(sortedList.get(i));
        }

        int count = 0;
        for (FlightRule rule : storage.getRules()) {
            assertEquals(rule, sortedList.get(count++));
        }

        injectBeans();
        int left = 0;
        int right = sortedList.size() - 1;
        while (left <= right) {
            storage.addRule(sortedList.get(left++));

            if (left <= right) {
                storage.addRule(sortedList.get(right--));
            }
        }

        count = 0;
        for (FlightRule rule : storage.getRules()) {
            assertEquals(rule, sortedList.get(count++));
        }
    }

    private PrioritizedFlightRule getDepartureInFutureRule() {
        return new PrioritizedFlightRule(
                "Departure is after current time",
                flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())),
                1
        );
    }

    private PrioritizedFlightRule getDepartureBeforeArrivalRule() {
        return new PrioritizedFlightRule(
                "Departure is before arrival",
                flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())),
                2
        );
    }

    private PrioritizedFlightRule getTimeOnGroundNotMore2HoursRule() {
        return new PrioritizedFlightRule(
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
                },
                3
        );
    }
}