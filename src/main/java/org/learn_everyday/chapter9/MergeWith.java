package org.learn_everyday.chapter9;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class MergeWith {

    private static final Logger log = LoggerFactory.getLogger(MergeWith.class);

    public static void main(String[] args) {
        demo1();
        demo2();
        Util.sleep(3);
    }

    private static void demo1() {
        Flux.merge(producer1(), producer2(), producer3())
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1().mergeWith(producer2())
                .mergeWith(producer3())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .transform(Util.fluxLogger("Producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(21, 22, 23)
                .transform(Util.fluxLogger("Producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.just(51, 52, 53)
                .transform(Util.fluxLogger("Producer3"))
                .delayElements(Duration.ofMillis(10));
    }

}
