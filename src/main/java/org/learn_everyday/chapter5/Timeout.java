package org.learn_everyday.chapter5;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Timeout {

    public static void main(String[] args) {
        singleTimeout();
        multipleTimeout();
    }

    private static void singleTimeout() {
        getProductName()
                .timeout(Duration.ofMillis(1000), fallback())
                .subscribe(Util.subscriber());

        Util.sleep(3);
    }

    private static void multipleTimeout() {

        var mono = getProductName()
                .timeout(Duration.ofMillis(1000), fallback());

        mono.timeout(Duration.ofMillis(200))
                        .subscribe(Util.subscriber());

        Util.sleep(3);
    }
    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "Service::"+ Util.fake().commerce().productName())
                .delayElement(Duration.ofMillis(900));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "Fallback::"+ Util.fake().commerce().productName())
                .delayElement(Duration.ofMillis(600));
    }

}
