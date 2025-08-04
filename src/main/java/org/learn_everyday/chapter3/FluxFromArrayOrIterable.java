package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxFromArrayOrIterable {

    public static void main(String[] args) {

        var list = List.of("a", "b", "c");
        Flux.fromIterable(list)
                .subscribe(Util.subscriber());

        Integer[] array = {1, 2, 3};
        Flux.fromArray(array)
                .subscribe(Util.subscriber());
    }

}
