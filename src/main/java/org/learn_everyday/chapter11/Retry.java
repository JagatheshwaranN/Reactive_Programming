package org.learn_everyday.chapter11;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Retry {

    private static final Logger log = LoggerFactory.getLogger(Retry.class);

    public static void main(String[] args) {
        retryWhenWithRetryExhaustedDemo();
        Util.sleep(10);
    }

    private static void retryDemo() {
        getCountryName().retry(2)
                .subscribe(Util.subscriber());
    }

    private static void retryWhenDemo() {
        getCountryName()
                .retryWhen(reactor.util.retry.Retry.max(3))
                .subscribe(Util.subscriber());
    }

    private static void retryWhenWithFixedDelayDemo() {
        getCountryName()
                .retryWhen(
                        reactor.util.retry.Retry
                                .fixedDelay(2, Duration.ofSeconds(1))
                                .doBeforeRetry(_ -> log.info("Retrying..")))
                .subscribe(Util.subscriber());
    }

    private static void retryWhenWithFilterDemo() {
        getCountryName()
                .retryWhen(
                        reactor.util.retry.Retry
                                .fixedDelay(2, Duration.ofSeconds(1))
                                .filter(ex -> RuntimeException.class.equals(ex.getClass())))
                .subscribe(Util.subscriber());
    }

    private static void retryWhenWithRetryExhaustedDemo() {
        getCountryName()
                .retryWhen(
                        reactor.util.retry.Retry
                                .fixedDelay(2, Duration.ofSeconds(1))
                                .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                                .onRetryExhaustedThrow((spec, signal) -> signal.failure()))
                .subscribe(Util.subscriber());
    }


    private static Mono<String> getCountryName() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (atomicInteger.incrementAndGet() < 5) {
                        throw new RuntimeException("oops");
                    }
                    return Util.fake().country().name();
                })
                .doOnError(err -> log.info("Error: {}", err.getMessage()))
                .doOnSubscribe(_ -> log.info("Subscribing..."));
    }

}
