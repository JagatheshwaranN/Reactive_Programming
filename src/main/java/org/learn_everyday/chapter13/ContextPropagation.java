package org.learn_everyday.chapter13;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class ContextPropagation {

    private static final Logger log = LoggerFactory.getLogger(ContextPropagation.class);

    public static void main(String[] args) {

        producerConcat();
        producersMerge();
        producer2Clear();
        Util.sleep(2);
    }

    private static void producerConcat() {
        getWelcomeMessage()
                .concatWith(producer1())
                .contextWrite(reactor.util.context.Context.of("user", "John"))
                .subscribe(Util.subscriber());
    }

    private static void producersMerge() {
        getWelcomeMessage()
                .concatWith(Flux.merge(producer1(), producer2()))
                .contextWrite(reactor.util.context.Context.of("user", "John"))
                .subscribe(Util.subscriber());
    }

    private static void producer2Clear() {
        getWelcomeMessage()
                .concatWith(Flux.merge(producer1(), producer2().contextWrite(ctx -> Context.empty())))
                .contextWrite(reactor.util.context.Context.of("user", "John"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
                    if (ctx.hasKey("user")) {
                        return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
                    }
                    return Mono.error(new RuntimeException("Unauthorized"));
                }
        );
    }

    private static Mono<String> producer1() {
        return Mono.<String>deferContextual(ctx -> {
                    log.info("Producer1 :{}", ctx);
                    return Mono.empty();
                }
        ).subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2() {
        return Mono.<String>deferContextual(ctx -> {
                    log.info("Producer2 :{}", ctx);
                    return Mono.empty();
                }
        ).subscribeOn(Schedulers.parallel());
    }

}
