package org.learn_everyday.chapter4;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
                    String country;
                    do {
                        country = Util.fake().country().name();
                        fluxSink.next(country);
                    } while (!country.equalsIgnoreCase("Japan"));
                    fluxSink.complete();
                })
                .subscribe(Util.subscriber());
    }
}
