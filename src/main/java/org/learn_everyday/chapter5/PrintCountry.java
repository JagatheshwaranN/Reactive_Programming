package org.learn_everyday.chapter5;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class PrintCountry {

    public static void main(String[] args) {

        Flux.<String>generate(synchronousSink -> synchronousSink.next(Util.fake().country().name()))
                .handle((item, sink) -> {
                    sink.next(item);
                    if (item.equalsIgnoreCase("Japan")) {
                        sink.complete();
                    }
                }).subscribe(Util.subscriber());
    }
}
