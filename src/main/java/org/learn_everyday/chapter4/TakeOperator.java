package org.learn_everyday.chapter4;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class TakeOperator {

    public static void main(String[] args) {
        take();
        System.out.println("*************************");
        takeWhile();
        System.out.println("*************************");
        takeUntil();
    }

    private static void take() {
        Flux.range(1, 10)
                .log("TAKE")
                .take(3)
                .log("SUB")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .log("TAKE")
                .takeWhile(i -> i > 5)
                .log("SUB")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .log("TAKE")
                .takeUntil(i -> i > 5)
                .log("SUB")
                .subscribe(Util.subscriber());
    }

}
