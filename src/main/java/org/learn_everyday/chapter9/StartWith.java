package org.learn_everyday.chapter9;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class StartWith {

    private static final Logger log = LoggerFactory.getLogger(StartWith.class);

    public static void main(String[] args) {
        demo1();
        System.out.println("***********************");
        demo2();
        System.out.println("***********************");
        demo3();
        System.out.println("***********************");
        demo4();

        Util.sleep(3);
    }

    private static void demo1() {
        producer1().startWith()
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1().startWith(-1, 0)
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        producer1().startWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        producer1().startWith(producer2())
                .startWith(-1, 0)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("Subscribing to Producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("Subscribing to Producer2"))
                .delayElements(Duration.ofMillis(10));
    }

}
