package org.learn_everyday.chapter14;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    private Flux<Integer> getItems() {
        return Flux.just(1, 2, 3)
                .log();
    }

    @Test
    public void fluxTest() {
        StepVerifier.create(getItems())
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    public void fluxTestWithAllValues() {
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTestWithAllValuesForm() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }

}
