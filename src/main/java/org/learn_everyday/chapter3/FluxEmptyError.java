package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxEmptyError {

    public static void main(String[] args) {

        Flux.empty()
                .subscribe(Util.subscriber());

        Flux.error(new RuntimeException("Flux Error"))
                .subscribe(Util.subscriber());
    }

}
