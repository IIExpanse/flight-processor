package com.gridnine.testing.tester.impl;

import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.flight.Segment;
import com.gridnine.testing.model.rule.PrioritizedFlightRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrioritizedFlightTesterTest {

    private PrioritizedFlightTester flightTester;

    @BeforeEach
    public void injectBeans() {
        flightTester = new PrioritizedFlightTester();
    }

    @Test
    public void shouldNotCallLeastPriorityRules() {
        List<Flight> badFlights = List.of(getDepartureInPastFlight(), getDepartureAfterArrivalFlight());
        flightTester.addRules(List.of(
                getDepartureInFutureRule(),
                getDepartureBeforeArrivalRule(),
                getExceptionThrowerRule())
        );

        assertThrows(
                AssertionError.class,
                () -> flightTester.filterFlights(List.of(getNormalFlight()), true)
        );
        assertDoesNotThrow(() -> flightTester.filterFlights(badFlights, true));
    }

    private Flight getNormalFlight() {
        return new Flight(List.of(new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2))));
    }

    private Flight getDepartureInPastFlight() {
        return new Flight(List.of(new Segment(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2))));
    }

    private Flight getDepartureAfterArrivalFlight() {
        return new Flight(List.of(new Segment(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(1))));
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

    private PrioritizedFlightRule getExceptionThrowerRule() {
        return new PrioritizedFlightRule(
                "Least priority rule exception thrower",
                flight -> {
                    throw new AssertionError("Least priority rule called");
                },
                Integer.MAX_VALUE
        );
    }
}