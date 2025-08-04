package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxMultipleSubscriber {

    public static void main(String[] args) {

        var flux = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.filter(i -> i % 2 == 0)
                .subscribe(Util.subscriber("Subscriber2"));

    }

}
