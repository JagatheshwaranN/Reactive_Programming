package org.learn_everyday.chapter9.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class MyTrip {

    public static Flux<Flight> getFlights() {
        return Flux.merge(Emirates.getFlights(), Qatar.getFlights(),
                        Cathay.getFlights())
                .take(Duration.ofSeconds(2));
    }

}
