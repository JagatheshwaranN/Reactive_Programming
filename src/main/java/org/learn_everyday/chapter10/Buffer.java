package org.learn_everyday.chapter10;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Buffer {

    public static void main(String[] args) {

        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        Util.sleep(60);
    }

    // Never Ending / Complete
    private static void demo1() {
        eventStream1()
                .buffer()
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        eventStream2()
                .buffer(3)
                .subscribe(Util.subscriber());
    }

    // Infinite Times
    private static void demo3() {
        eventStream1()
                .buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        eventStream3()
                .buffer(3)
                .subscribe(Util.subscriber());
    }

    private static void demo5() {
        eventStream3()
                .bufferTimeout(3, Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream1() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> "event-" + (i + 1));
    }

    private static Flux<String> eventStream2() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .map(i -> "event-" + (i + 1));
    }

    private static Flux<String> eventStream3() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never())
                .map(i -> "event-" + (i + 1));
    }

}
