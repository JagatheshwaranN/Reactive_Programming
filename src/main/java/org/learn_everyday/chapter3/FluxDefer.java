package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxDefer {

    public static void main(String[] args) {

        var list = List.of("A", "B", "C");
        Flux.fromIterable(list)
                .subscribe(Util.subscriber());

        Flux.defer(() -> Flux.fromIterable(createList()))
                .subscribe(Util.subscriber());

    }

    public static List<Integer> createList() {
        Util.sleep(1);
        return List.of(1, 2, 3, 4, 5);

    }

}
