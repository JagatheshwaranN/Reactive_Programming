package org.learn_everyday.chapter7;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOn {

    private static final Logger log = LoggerFactory.getLogger(PublishOn.class);

    public static void main(String[] args) {
        publishOnDemo();
        System.out.println("***********************");
        multiplePublishOnDemo();
    }


    private static void publishOnDemo() {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("Generated Value: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(obj -> log.info("Having Value {}", obj))
                .doFirst(() -> log.info("DoFirst1"))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("DoFirst2"));

        Runnable runnable1 = () -> flux
                .subscribe(Util.subscriber("Subscriber1"));
        Thread.ofPlatform().start(runnable1);

        Util.sleep(2);
    }

    private static void multiplePublishOnDemo() {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("Generated Value {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doOnNext(obj -> log.info("Have Value {}", obj))
                .doFirst(() -> log.info("Do First1"))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("Do First2"));

        Runnable runnable1 = () -> flux
                .subscribe(Util.subscriber("Subscriber1"));
        Thread.ofPlatform().start(runnable1);

        Util.sleep(2);
    }

}
