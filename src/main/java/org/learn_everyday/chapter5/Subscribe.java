package org.learn_everyday.chapter5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Subscribe {

    private static final Logger log = LoggerFactory.getLogger(Subscribe.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .doOnNext(i -> log.info("Received: {}", i))
                .doOnComplete(() -> log.info("Completed!"))
                .doOnError(err -> log.error("Error: ", err))
                .subscribe();
    }

}
