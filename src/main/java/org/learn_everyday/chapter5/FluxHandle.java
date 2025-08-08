package org.learn_everyday.chapter5;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxHandle {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .handle((item, sink) -> {
                    switch(item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {}
                        case 7 -> sink.error(new RuntimeException("OOPS"));
                        default -> sink.next(item);
                    }
                }).subscribe(Util.subscriber());
    }
}
