package org.learn_everyday.chapter9.helper;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qatar {

    private static final String AIRLINES = "Qatar";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.fake().random().nextInt(3, 5))
                .delayElements(Duration.ofMillis(Util.fake().random().nextInt(300, 800)))
                .map(obj -> new Flight(AIRLINES, Util.fake().random().nextInt(400, 900)))
                .transform(Util.fluxLogger(AIRLINES));
    }

}
