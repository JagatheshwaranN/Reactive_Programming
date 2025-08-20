package org.learn_everyday.chapter9.user_order_service;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static final Map<String, List<Order>> orderTable = Map.of(
            "Sam", List.of(
                    new Order("Sam", Util.fake().commerce().productName(), Util.fake().random().nextInt(10, 100)),
                    new Order("Sam", Util.fake().commerce().productName(), Util.fake().random().nextInt(10, 100))),
            "Alex", List.of(
                    new Order("Alex", Util.fake().commerce().productName(), Util.fake().random().nextInt(10, 100)),
                    new Order("Alex", Util.fake().commerce().productName(), Util.fake().random().nextInt(10, 100)),
                    new Order("Alex", Util.fake().commerce().productName(), Util.fake().random().nextInt(10, 100))),
            "Erick", List.of());

    public static Flux<Order> getUserOrders(String username) {
        return Flux.fromIterable(orderTable.get(username)).delayElements(Duration.ofMillis(500))
                .transform(Util.fluxLogger(username));
    }

}
