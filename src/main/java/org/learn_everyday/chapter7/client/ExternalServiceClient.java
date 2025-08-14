package org.learn_everyday.chapter7.client;

import org.learn_everyday.reuse.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

        public Mono<String> getProductName(int productId) {
            return this.httpClient.get()
                    .uri("/demo01/product/"+productId)
                    .responseContent()
                    .asString()
                    .doOnNext(message  -> log.info("Next: {}", message))
                    .next()
                    .publishOn(Schedulers.boundedElastic());
        }
}
