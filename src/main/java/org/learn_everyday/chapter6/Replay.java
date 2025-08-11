package org.learn_everyday.chapter6;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Replay {

    private static final Logger log = LoggerFactory.getLogger(Replay.class);

    public static void main(String[] args) {
        var stockFlux = stockStream().replay(1).autoConnect(0);
        Util.sleep(4);
        log.info("Subscriber1 Joins..");
        stockFlux.subscribe(Util.subscriber("Subscriber1"));
        Util.sleep(4);
        log.info("Subscriber2 Joins..");
        stockFlux.subscribe(Util.subscriber("Subscriber2"));
        Util.sleep(15);

    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.fake().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("Current Price: {}", price))
                .cast(Integer.class);
    }

}
