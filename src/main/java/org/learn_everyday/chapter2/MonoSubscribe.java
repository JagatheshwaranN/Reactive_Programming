package org.learn_everyday.chapter2;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(MonoSubscribe.class);


    public static void main(String[] args) {

        var mono = Mono.just(1);

        mono.subscribe(
                i -> log.info("received: {}", i),
                error -> log.error("error: ", error),
                () -> log.info("completed!"),
                subscription -> subscription.request(1));
    }

}
