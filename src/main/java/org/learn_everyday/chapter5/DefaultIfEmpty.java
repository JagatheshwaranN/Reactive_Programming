package org.learn_everyday.chapter5;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DefaultIfEmpty {

    public static void main(String[] args) {
        monoDefaultIfEmpty();
        fluxDefaultIfEmpty();
    }

    private static void monoDefaultIfEmpty() {
        Mono.just("I'm Mono")
                .defaultIfEmpty("I'm Default")
                .subscribe(Util.subscriber());
        System.out.println("************************");
        Mono.empty()
                .defaultIfEmpty("I'm Default")
                .subscribe(Util.subscriber());
    }

    private static void fluxDefaultIfEmpty() {
        Flux.range(1, 10)
                .filter(i -> i > 10)
                .defaultIfEmpty(0)
                .subscribe(Util.subscriber());
    }

}
