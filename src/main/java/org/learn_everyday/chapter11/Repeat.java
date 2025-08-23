package org.learn_everyday.chapter11;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Repeat {

    public static void main(String[] args) {

        repeatDemo();
        repeatWithCountDemo();
        repeatWithTakeUntilDemo();
        repeatWithAtomicIntegerDemo();
        repeatWhenDemo();
        fluxRepeatDemo();
        Util.sleep(10);
    }

    //Infinite Repeat
    private static void repeatDemo() {
        var subscriber = Util.subscriber();
        getCountryName().repeat().subscribe(subscriber);
    }

    // Conditional Repeat
    private static void repeatWithCountDemo() {
        var subscriber = Util.subscriber();
        getCountryName().repeat(5).subscribe(subscriber);
    }

    private static void repeatWithTakeUntilDemo() {
        var subscriber = Util.subscriber();
        getCountryName().repeat()
                .takeUntil(obj -> obj.equalsIgnoreCase("Canada"))
                .subscribe(subscriber);
    }

    private static void repeatWithAtomicIntegerDemo() {
        var atomicInteger = new AtomicInteger(0);
        var subscriber = Util.subscriber();
        getCountryName().repeat(() -> atomicInteger.incrementAndGet() < 3)
                .subscribe(subscriber);
    }

    private static void repeatWhenDemo() {
        var subscriber = Util.subscriber();
        getCountryName().
                repeatWhen(longFlux ->
                        longFlux.delayElements(Duration.ofSeconds(2)))
                .subscribe(subscriber);
    }

    private static void fluxRepeatDemo() {
        Flux.range(1, 3)
                .repeat(3)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        return Mono.fromSupplier(() -> Util.fake().country().name());
    }

}