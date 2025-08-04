package org.learn_everyday.chapter3.client;

import org.learn_everyday.reuse.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

        public Flux<String> getNames() {
            return this.httpClient.get()
                    .uri("/demo02/name/stream")
                    .responseContent()
                    .asString();
        }

}
