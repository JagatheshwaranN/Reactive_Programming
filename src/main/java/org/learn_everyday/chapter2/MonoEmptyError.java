package org.learn_everyday.chapter2;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyError {

    public static void main(String[] args) {

        for(int i = 1; i <=3 ; i++){
            getUserName(i)
                    .subscribe(Util.subscriber());
            System.out.println("==============================");
        }

//         ErrorDropped
//        getUserName(3)
//                .subscribe(s -> System.out.println(s));

        getUserName(3)
                .subscribe(System.out::println,
                        err -> {});

    }

    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Subscriber1");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid Input"));
        };
    }
}
