package org.learn_everyday.chapter14;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class ContextTest {

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
                    if (ctx.hasKey("user")) {
                        return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
                    }
                    return Mono.error(new RuntimeException("Unauthorized"));
                }
        );
    }

    @Test
    public void welcomeTest() {
        var options = StepVerifierOptions.create().withInitialContext(Context.of("user","John"));
        StepVerifier.create(getWelcomeMessage(), options)
                .expectNext("Welcome John")
                .expectComplete()
                .verify();

    }

    @Test
    public void unauthorizedTest() {
        var options = StepVerifierOptions.create().withInitialContext(Context.empty());
        StepVerifier.create(getWelcomeMessage(), options)
                .expectErrorMessage("Unauthorized")
                .verify();

    }

}
