package org.learn_everyday.chapter7;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOn {

    private static final Logger log = LoggerFactory.getLogger(SubscribeOn.class);

    public static void main(String[] args) {
        subscribeOnDemo();
        System.out.println("******************************");
        subscribeOnRunnableDemo();
        System.out.println("******************************");
        subscribeOnWithMultiRunnableDemo();
    }

    private static void subscribeOnDemo() {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("Generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(obj -> log.info("Value: {}", obj));

        flux.doFirst(() -> log.info("First1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"))
                .subscribe(Util.subscriber());

        Util.sleep(2);
    }

    private static void subscribeOnRunnableDemo() {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("Generating {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(obj -> log.info("Value {}", obj));

        Runnable runnable = () -> flux.doFirst(() -> log.info("doFirst1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("doFirst2"))
                .subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable);

        Util.sleep(2);
    }

    private static void subscribeOnWithMultiRunnableDemo() {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("Generated Value: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(obj -> log.info("Having Value {}", obj))
                .doFirst(() -> log.info("DoFirst1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("DoFirst2"));

        Runnable runnable1 = () -> flux
                .subscribe(Util.subscriber("Subscriber1"));
        Runnable runnable2 = () -> flux
                .subscribe(Util.subscriber("Subscriber2"));
        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable2);

        Util.sleep(2);
    }

}
