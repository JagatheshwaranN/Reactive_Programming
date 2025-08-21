package org.learn_everyday.chapter9;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CollectList {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .collectList()
                .subscribe(Util.subscriber());

        Flux.range(1, 20)
                .concatWith(Mono.error(new RuntimeException("oops")))
                .collectList()
                .subscribe(Util.subscriber());
    }

}
