package org.learn_everyday.chapter4;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateWithState {

    public static void main(String[] args) {

        Flux.generate(() -> 0,
                (counter, sink) -> {
                    var country = Util.fake().country().name();
                    sink.next(country);
                    counter++;
                    if (counter == 10 || country.equalsIgnoreCase("Japan")) {
                        sink.complete();
                    }
                    return counter;
                }).subscribe(Util.subscriber());

    }

}
