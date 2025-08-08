package org.learn_everyday.chapter5;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class DoCallBacks {

    private static final Logger log = LoggerFactory.getLogger(DoCallBacks.class);

    public static void main(String[] args) {

        Flux.<Integer>create(integerFluxSink -> {
            log.info("Producer Begins");
            for(int i = 0; i < 4; i++) {
                integerFluxSink.next(i);
            }
            integerFluxSink.complete();
            log.info("Producer Ends");
        })
                .doOnComplete(() -> log.info("doOnComplete-1"))
                .doFirst(() -> log.info("doFirst-1"))
                .doOnNext(item -> log.info("doOnNext-1: {}",item))
                .doOnSubscribe(subscription -> log.info("doOnSubscribe-1: {}",subscription))
                .doOnRequest(request -> log.info("doOnRequest-1: {}", request))
                .doOnError(error -> log.info("doOnError-1: {}", error.getMessage()))
                .doOnTerminate(() -> log.info("doOnTerminate-1"))
                .doOnCancel(() -> log.info("doOnCancel-1"))
                .doOnDiscard(Object.class, obj -> log.info("doOnDiscard-1: {}", obj))
                .doFinally(signalType -> log.info("doFinally-1: {}", signalType))
                .take(2)
                .doOnComplete(() -> log.info("doOnComplete-2"))
                .doFirst(() -> log.info("doFirst-2"))
                .doOnNext(item -> log.info("doOnNext-2: {}",item))
                .doOnSubscribe(subscription -> log.info("doOnSubscribe-2: {}",subscription))
                .doOnRequest(request -> log.info("doOnRequest-2: {}", request))
                .doOnError(error -> log.info("doOnError-2: {}", error.getMessage()))
                .doOnTerminate(() -> log.info("doOnTerminate-2"))
                .doOnCancel(() -> log.info("doOnCancel-2"))
                .doOnDiscard(Object.class, obj -> log.info("doOnDiscard-2: {}", obj))
                .doFinally(signalType -> log.info("doFinally-2: {}", signalType))
                .take(4)
                .subscribe(Util.subscriber());

    }
}
