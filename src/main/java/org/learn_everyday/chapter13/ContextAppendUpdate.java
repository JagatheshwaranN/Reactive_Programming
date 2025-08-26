package org.learn_everyday.chapter13;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class ContextAppendUpdate {

    private static final Logger log = LoggerFactory.getLogger(ContextAppendUpdate.class);

    public static void main(String[] args) {
        contextAppend();
        contextUpdateModify();
        contextUpdateDelete();
    }

    private static void contextAppend() {
        getWelcomeMessage()
                .contextWrite(reactor.util.context.Context.of("mob", "123").put("loc", "US"))
                .contextWrite(reactor.util.context.Context.of("user", "John"))
                .subscribe(Util.subscriber());
    }

    private static void contextUpdateModify() {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("user", "Alex"))
                .contextWrite(reactor.util.context.Context.of("mob", "123").put("loc", "US"))
                .contextWrite(reactor.util.context.Context.of("user", "John"))
                .subscribe(Util.subscriber());
    }

    private static void contextUpdateDelete() {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.delete("user"))
                .contextWrite(reactor.util.context.Context.of("mob", "123").put("loc", "US"))
                .contextWrite(reactor.util.context.Context.of("user", "John"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
                    log.info("{}", ctx);
                    if (ctx.hasKey("user")) {
                        return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
                    }
                    return Mono.error(new RuntimeException("oops"));
                }
        );
    }

}
