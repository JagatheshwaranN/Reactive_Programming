package org.learn_everyday.chapter2.client;

import org.learn_everyday.reuse.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

        public Mono<String> getProductName(int productId) {
            return this.httpClient.get()
                    .uri("/demo01/product/"+productId)
                    .responseContent()
                    .asString()
                    .next();
        }
}
