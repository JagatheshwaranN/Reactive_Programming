package org.learn_everyday.chapter5;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorHandle {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandle.class);

    public static void main(String[] args) {
        fluxOnErrorReturn();
        monoOnErrorReturn();
        monoOnErrorResume();
        monoOnErrorReturnWithErrorResume();
        onErrorComplete();
        fluxOnErrorContinue();
    }

    private static void fluxOnErrorReturn() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorReturn(ArithmeticException.class, -1)
                .onErrorReturn(IllegalArgumentException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    private static void monoOnErrorReturn() {
        Mono.just(5)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorReturn(ArithmeticException.class, -1)
                .onErrorReturn(IllegalArgumentException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    private static void monoOnErrorResume() {
        Mono.just(5)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorResume(ex -> fallback())
                .subscribe(Util.subscriber());
    }

    private static void monoOnErrorReturnWithErrorResume() {
        Mono.error(new ArithmeticException())
                .onErrorResume(ArithmeticException.class, ex -> fallback())
                .onErrorResume(ex -> fallback2())
                .onErrorReturn(-1)
                .subscribe(Util.subscriber());
    }

    private static void onErrorComplete() {
        Mono.just(1)
                .onErrorComplete()
                .subscribe(Util.subscriber());
        System.out.println("*****************");
        Mono.error(new RuntimeException("err"))
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    private static void fluxOnErrorContinue() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorContinue((ex, obj) -> log.error("ERR: {}", obj, ex))
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.fake().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.fake().random().nextInt(100, 1000));
    }

}
