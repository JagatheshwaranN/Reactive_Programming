package org.learn_everyday.chapter9.user_order_service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {

    private static final Map<Integer, String> userTable = Map.of(1, "Sam",
            2, "Alex", 3, "Erick");

    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(userTable.entrySet())
                .map(entry -> new User(entry.getKey(), entry.getValue()));
    }

    public static Mono<String> getUserName(Integer id) {
        return Mono.fromSupplier(() -> userTable.get(id));
    }
}
