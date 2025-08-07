package org.learn_everyday.chapter4;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateEmitUntil {

    public static void main(String[] args) {

        generateType1();
        System.out.println("************************");
        generateType2();
    }

    private static void generateType1() {
        Flux.generate(synchronousSink -> {
            String country = Util.fake().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("Japan")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }

    private static void generateType2() {
        Flux.<String>generate(synchronousSink -> {
                    String country = Util.fake().country().name();
                    synchronousSink.next(country);
                })
                .takeUntil(c -> c.equalsIgnoreCase("Japan"))
                .subscribe(Util.subscriber());
    }

}
