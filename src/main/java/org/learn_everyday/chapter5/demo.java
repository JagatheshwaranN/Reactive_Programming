package org.learn_everyday.chapter5;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class demo {

    public static void main(String[] args) {

        Flux.range(1, 100)
                .take(25)
                .takeWhile(i -> i < 10)
                .takeUntil(i -> i > 1 && i < 5)
                .take(3)
                .subscribe(Util.subscriber());
    }
}
