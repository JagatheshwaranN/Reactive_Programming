package org.learn_everyday.chapter11;

import org.learn_everyday.chapter11.client.ExternalServiceClient;
import org.learn_everyday.chapter11.client.ServerError;
import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RepeatRetryExternalService {

    private static final Logger log = LoggerFactory.getLogger(RepeatRetryExternalService.class);

    public static void main(String[] args) {
        repeat();
        retry();
        Util.sleep(60);
    }

    private static void repeat() {
        var client = new ExternalServiceClient();
        client.getCountryName()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("Canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry() {
        var client = new ExternalServiceClient();
        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError() {
        return Retry.fixedDelay(10, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> log.info("Retrying {}", rs.failure().getMessage()));
    }

}
