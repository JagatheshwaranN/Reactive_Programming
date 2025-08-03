package org.learn_everyday.chapter2;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MonoDefer {

    private static final Logger log = LoggerFactory.getLogger(MonoDefer.class);

    public static void main(String[] args) {
        Mono.defer(MonoDefer::createPublisher)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> createPublisher() {
        log.info("Creating Publisher");
        var list = List.of(1, 2, 3, 4, 5);
        Util.sleep(1);
        return Mono.fromSupplier(() -> sum(list));
    }

    private static int sum(List<Integer> list) {
        log.info("Finding the sum of {}", list);
        Util.sleep(2);
        return list.stream().mapToInt(num -> num).sum();
    }
}
