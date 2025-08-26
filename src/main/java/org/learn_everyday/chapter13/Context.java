package org.learn_everyday.chapter13;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Context {

    private static final Logger log = LoggerFactory.getLogger(Context.class);

    public static void main(String[] args) {

        getWelcomeMessage()
                .contextWrite(reactor.util.context.Context.of("user", "John"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
                    if (ctx.hasKey("user")) {
                        return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
                    }
                    return Mono.error(new RuntimeException("oops"));
                }
        );
    }

}
