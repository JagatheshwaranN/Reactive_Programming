package org.learn_everyday.chapter14;

import org.junit.jupiter.api.Test;
import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxRangeTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 50)
                .log();
    }

    private Flux<Integer> getRandomItems() {
        return Flux.range(1, 50)
                .map(i -> Util.fake().random().nextInt(1, 100));
    }

    @Test
    public void fluxRangeTest() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectNextCount(45)
                .verifyComplete();
    }

    @Test
    public void fluxRangeTestType2() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectNextCount(20)
                .expectNext(26, 27, 28, 29, 30)
                .expectNextCount(20)
                .verifyComplete();
    }

    @Test
    public void fluxRandomRangeTest() {
        StepVerifier.create(getRandomItems())
                .expectNextMatches(i -> i > 0 && i < 101)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

}
