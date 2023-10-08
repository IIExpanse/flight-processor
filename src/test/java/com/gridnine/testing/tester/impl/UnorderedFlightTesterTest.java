package com.gridnine.testing.tester.impl;

import com.gridnine.testing.model.flight.Flight;
import com.gridnine.testing.model.flight.Segment;
import com.gridnine.testing.model.rule.PrioritizedFlightRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnorderedFlightTesterTest {

    private UnorderedFlightTester flightTester;

    @BeforeEach
    public void injectBeans() {
        flightTester = new UnorderedFlightTester();
    }

    @Test
    public void filterFlightsTest() {
        Collection<Flight> flights = List.of(
                getNormalFlight(),
                getDepartureInPastFlight(),
                getDepartureAfterArrivalFlight(),
                get2DaysOnGroundFlight()
        );
        int l = flights.size();

        assertEquals(l, flightTester.filterFlights(flights, true).size());

        flightTester.addRule(getDepartureInFutureRule());
        l--;
        assertEquals(l, flightTester.filterFlights(flights, true).size());

        flightTester.addRule(getDepartureBeforeArrivalRule());
        l--;
        assertEquals(l, flightTester.filterFlights(flights, true).size());

        flightTester.addRule(getTimeOnGroundNotMore2HoursRule());
        l--;
        assertEquals(l, flightTester.filterFlights(flights, true).size());

        assertEquals(flights.size() - l, flightTester.filterFlights(flights, false).size());
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

    private Flight get2DaysOnGroundFlight() {
        return new Flight(List.of(
                new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2)),
                new Segment(LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(5))
        ));
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