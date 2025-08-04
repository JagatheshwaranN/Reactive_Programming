package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxFromStream {

    public static void main(String[] args) {

        var list = List.of(1, 2, 3, 4);
        var flux = Flux.fromStream(list::stream);

        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.subscribe(Util.subscriber("Subscriber2"));
    }

}
