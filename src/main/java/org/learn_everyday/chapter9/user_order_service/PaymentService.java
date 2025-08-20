package org.learn_everyday.chapter9.user_order_service;

import reactor.core.publisher.Mono;

import java.util.Map;

public class PaymentService {

    private static final Map<String, Integer> userBalanceTable = Map.of(
            "Sam", 100,
            "Alex", 200,
            "Erick", 300
    );

    public static Mono<Integer> getUserBalance(String username) {
        return Mono.fromSupplier(() -> userBalanceTable.get(username));
    }

}
