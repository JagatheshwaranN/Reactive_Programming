package org.learn_everyday.chapter9.helper;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Emirates {

    private static final String AIRLINES = "Emirates";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.fake().random().nextInt(2, 10))
                .delayElements(Duration.ofMillis(Util.fake().random().nextInt(200, 1000)))
                .map(obj -> new Flight(AIRLINES, Util.fake().random().nextInt(300, 1000)))
                .transform(Util.fluxLogger(AIRLINES));
    }

}
