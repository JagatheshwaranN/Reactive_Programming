package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxInterval {

    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(500))
                .map(i -> Util.fake().name().firstName())
                .subscribe(Util.subscriber());
        Util.sleep(5);
    }

}
