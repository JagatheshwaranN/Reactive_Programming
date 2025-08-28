package org.learn_everyday.chapter14;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class PublisherTest {

    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux
                .filter(val -> val.length() > 1)
                .map(String::toUpperCase)
                .map(val -> val + ":" + val.length());
    }

    @Test
    public void publisherTest() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();
        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("Hello", "Welcome"))
                .expectNext("HELLO:5")
                .expectNext("WELCOME:7")
                .expectComplete()
                .verify();
    }

}
