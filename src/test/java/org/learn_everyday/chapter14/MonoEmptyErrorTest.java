package org.learn_everyday.chapter14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoEmptyErrorTest {

    private static final Logger log = LoggerFactory.getLogger(MonoEmptyErrorTest.class);

    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Subscriber1");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid Input"));
        };
    }

    @Test
    public void userTest() {
        StepVerifier.create(getUserName(1))
                .expectNext("Subscriber1")
                .expectComplete()
                .verify();
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUserName(2))
                .expectComplete()
                .verify();
    }

    @Test
    public void errorTest() {
        StepVerifier.create(getUserName(3))
                .expectError()
                .verify();
    }

    @Test
    public void errorTestWithException() {
        StepVerifier.create(getUserName(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void errorTestWithExceptionMessage() {
        StepVerifier.create(getUserName(3))
                .expectErrorMessage("Invalid Input")
                .verify();
    }

    @Test
    public void errorTestWithCustomization() {
        StepVerifier.create(getUserName(3))
                .consumeErrorWith(err -> {
                    Assertions.assertEquals(RuntimeException.class, err.getClass());
                    Assertions.assertEquals("Invalid Input", err.getMessage());
                })
                .verify();
    }

}
