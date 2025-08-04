package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxRange {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .subscribe(Util.subscriber());

        Flux.range(3, 5)
                .subscribe(Util.subscriber());

        Flux.range(1, 5)
                .map(i -> Util.fake().name().fullName())
                .subscribe(Util.subscriber());
    }

}
