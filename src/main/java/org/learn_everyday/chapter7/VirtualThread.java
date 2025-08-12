package org.learn_everyday.chapter7;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class VirtualThread {

    private static final Logger log = LoggerFactory.getLogger(VirtualThread.class);

    public static void main(String[] args) {

        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("Generated Value: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(obj -> log.info("Having Value {}", obj))
                .doFirst(() -> log.info("DoFirst1-{}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("DoFirst2"));

        Runnable runnable1 = () -> flux
                .subscribe(Util.subscriber("Subscriber1"));
        Thread.ofPlatform().start(runnable1);

        Util.sleep(2);
    }

}
