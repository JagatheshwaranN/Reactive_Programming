package org.learn_everyday.chapter7;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class ThreadDefaultBehaviour {

    private static final Logger log = LoggerFactory.getLogger(ThreadDefaultBehaviour.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("Generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(obj -> log.info("Value: {}", obj));

        // flux.subscribe(Util.subscriber()); - Main Thread

        // Child Thread
        Runnable runnable = () -> {
            flux.subscribe(Util.subscriber());
        };
        Thread.ofPlatform().start(runnable);
    }

}
