package org.learn_everyday.chapter4;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            synchronousSink.next(1);
            synchronousSink.complete();
        }).subscribe(Util.subscriber());

        Flux.generate(synchronousSink -> {
                    synchronousSink.next(1);
                }).take(3)
                .subscribe(Util.subscriber());

        Flux.generate(synchronousSink -> {
            synchronousSink.next(1);
            synchronousSink.error(new RuntimeException("Exception Occurred"));
        }).subscribe(Util.subscriber());

    }

}
