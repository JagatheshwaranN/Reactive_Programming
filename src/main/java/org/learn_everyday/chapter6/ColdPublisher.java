package org.learn_everyday.chapter6;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class ColdPublisher {

    private static final Logger log = LoggerFactory.getLogger(ColdPublisher.class);

    public static void main(String[] args) {

        maintainStateIn();
        maintainStateOut();
    }

    private static void maintainStateIn() {
        // COLD Publisher
        var flux = Flux.create(fluxSink -> {
            log.info("Invoked..");
            for(int i = 0; i < 3; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        });
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.subscribe(Util.subscriber("Subscriber2"));
    }

    private static void maintainStateOut() {
        AtomicInteger integer = new AtomicInteger(0);
        var flux = Flux.create(fluxSink -> {
            log.info("Invoked");
            for(int i = 0; i < 3; i++) {
                fluxSink.next(integer.incrementAndGet());
            }
            fluxSink.complete();
        });
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.subscribe(Util.subscriber("Subscriber2"));
    }

}
