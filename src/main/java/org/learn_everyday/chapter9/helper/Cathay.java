package org.learn_everyday.chapter9.helper;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Cathay {

    private static final String AIRLINES = "Cathay";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.fake().random().nextInt(5, 10))
                .delayElements(Duration.ofMillis(Util.fake().random().nextInt(200, 1200)))
                .map(obj -> new Flight(AIRLINES, Util.fake().random().nextInt(300, 1200)))
                .transform(Util.fluxLogger(AIRLINES));
    }

}
