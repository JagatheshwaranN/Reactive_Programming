package org.learn_everyday.chapter6;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AutoConnect {

    private static final Logger log = LoggerFactory.getLogger(AutoConnect.class);

    public static void main(String[] args) {

        var movieFlux = movieStream().publish().autoConnect(0);
        Util.sleep(2);
        movieFlux.subscribe(Util.subscriber("John"));
        Util.sleep(3);
        movieFlux.
                take(2)
                .subscribe(Util.subscriber("Alex"));
        Util.sleep(10);
    }

    // Movie Theater
    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("Request Received..");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "Movie Scene " + state;
                            log.info("Playing {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                ).take(7)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }

}
