package org.learn_everyday.chapter2;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFuture {

    private static final Logger log = LoggerFactory.getLogger(MonoFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(MonoFuture::getName)
                .subscribe(Util.subscriber());
        Util.sleep(1);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Generating Name");
            return Util.fake().name().fullName();
        });
    }
}
