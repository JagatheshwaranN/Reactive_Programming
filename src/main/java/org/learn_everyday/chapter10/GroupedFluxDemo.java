package org.learn_everyday.chapter10;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class GroupedFluxDemo {

    private static final Logger log = LoggerFactory.getLogger(GroupedFluxDemo.class);

    public static void main(String[] args) {

        groupedFluxDemo();
        groupedFluxUpdatedDemo();
        Util.sleep(60);
    }

    private static void groupedFluxDemo() {
        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i % 2)
                .flatMap(GroupedFluxDemo::processEvents)
                .subscribe();
    }

    private static void groupedFluxUpdatedDemo() {
        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> i * 2)
                .startWith(1)
                .groupBy(i -> i % 2)
                .flatMap(GroupedFluxDemo::processEvents)
                .subscribe();

    }

    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> groupedFlux) {
        log.info("Received Flux for {}", groupedFlux.key());
        return groupedFlux.doOnNext(i -> log.info("Key: {}, Item: {}", groupedFlux.key(), i))
                .doOnComplete(() -> log.info("{} completed", groupedFlux.key()))
                .then();
    }

}
