package org.learn_everyday.chapter6.orderStreamService;

import reactor.core.publisher.Flux;

public interface OrderProcessor {

    void consume(Order order);

    Flux<String> stream();

}
